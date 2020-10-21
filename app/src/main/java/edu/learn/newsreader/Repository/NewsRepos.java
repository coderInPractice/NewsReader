package edu.learn.newsreader.Repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import edu.learn.newsreader.Database.NewsRoomDatabase;
import edu.learn.newsreader.Modals.Article;
import edu.learn.newsreader.Modals.NewsStatus;
import edu.learn.newsreader.Networking.NewsInterface;
import edu.learn.newsreader.Networking.RetrofitSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepos {
    Context context;

    private LiveData<List<Article>> allNews;
    private NewsRoomDatabase db;

    public NewsRepos(Application application){
        context = application.getApplicationContext();
        db = NewsRoomDatabase.getInstance(application);
        allNews = db.newsDao().getAllNews();
    }

    private MutableLiveData<List<Article>> mutableNewsArticle = new MutableLiveData<>();
    private List<Article> mArticleList = new ArrayList<>();

    public void getNewsFromApi(String country, String api_key, SwipeRefreshLayout swipeRefreshLayout){
        deletePrevResponse();
        NewsInterface newsInterface = RetrofitSingleton.getApiClient().create(NewsInterface.class);
        Call<NewsStatus> call = newsInterface.getNews(country,api_key);
        call.enqueue(new Callback<NewsStatus>() {
            @Override
            public void onResponse(@NotNull Call<NewsStatus> call, @NotNull Response<NewsStatus> response) {
                if(response.isSuccessful() || response.body().getArticle() != null){
                    mArticleList = response.body().getArticle();
                    mutableNewsArticle.postValue(mArticleList);

                    swipeRefreshLayout.setRefreshing(false);

                    insertApiResponseIntoDb(mArticleList);
                }
                else{
                    Log.d("Repository api call: ", "onResponse is empty or unsuccessful");
                }
            }

            @Override
            public void onFailure(Call<NewsStatus> call, Throwable t) {

                Toast.makeText(context, "Api response is failed", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void deletePrevResponse() {
        NewsRoomDatabase.databaseWriteExecutor.execute(() ->{
            db.newsDao().deleteAllNews();
        });
    }

    private void insertApiResponseIntoDb(List<Article> mArticleList) {
        Log.d("insertApiResponseInDb: ", "called");
        NewsRoomDatabase.databaseWriteExecutor.execute(()->{
            db.newsDao().insert(mArticleList);
        });
    }

    public LiveData<List<Article>> getAllNews(){
        return allNews;
    }
}
