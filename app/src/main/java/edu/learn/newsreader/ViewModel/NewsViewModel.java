package edu.learn.newsreader.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.learn.newsreader.Modals.Article;
import edu.learn.newsreader.Repository.NewsRepos;

public class NewsViewModel extends AndroidViewModel {
    private LiveData<List<Article>> newsLiveData;
    private NewsRepos newsRepos;
    public NewsViewModel(@NonNull Application application) {
        super(application);
        newsRepos = new NewsRepos(application);
        newsLiveData = newsRepos.getAllNews();
    }

    public void startApiCall(String country, String api_key){
        newsRepos.getNewsFromApi(country,api_key);
    }

    public LiveData<List<Article>> getAllNews(){
        return newsLiveData;
    }
}
