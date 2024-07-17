package edu.learn.newsreader.Repository

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import edu.learn.newsreader.Database.NewsRoomDatabase
import edu.learn.newsreader.Database.NewsRoomDatabase.Companion.getInstance
import edu.learn.newsreader.Modals.Article
import edu.learn.newsreader.Modals.NewsStatus
import edu.learn.newsreader.Networking.NewsInterface
import edu.learn.newsreader.Networking.RetrofitSingleton.apiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepos(application: Application) {
    var context: Context = application.applicationContext

    val allNews: LiveData<List<Article?>?>?
    private val db = getInstance(application)

    private val mutableNewsArticle = MutableLiveData<List<Article?>?>()
    private var mArticleList: List<Article?>? = ArrayList()

    init {
        allNews = db!!.newsDao()!!.allNews
    }

    fun getNewsFromApi(country: String?, apiKey: String?, swipeRefreshLayout: SwipeRefreshLayout) {
        deletePrevResponse()
        val newsInterface = apiClient!!.create(
            NewsInterface::class.java
        )
        val call = newsInterface.getNews(country, apiKey)
        call!!.enqueue(object : Callback<NewsStatus?> {
            override fun onResponse(call: Call<NewsStatus?>, response: Response<NewsStatus?>) {
                if (response.isSuccessful || response.body()!!.article != null) {
                    mArticleList = response.body()!!.article
                    mutableNewsArticle.postValue(mArticleList)

                    swipeRefreshLayout.isRefreshing = false

                    insertApiResponseIntoDb(mArticleList)
                } else {
                    swipeRefreshLayout.isRefreshing = false
                    Log.d("Repository api call: ", "onResponse is empty or unsuccessful")
                }
            }

            override fun onFailure(call: Call<NewsStatus?>, t: Throwable) {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(context, "Api response is failed", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun deletePrevResponse() {
        NewsRoomDatabase.databaseWriteExecutor.execute {
            db!!.newsDao()!!.deleteAllNews()
        }
    }

    private fun insertApiResponseIntoDb(mArticleList: List<Article?>?) {
        Log.d("insertApiResponseInDb: ", "called")
        NewsRoomDatabase.databaseWriteExecutor.execute {
            db!!.newsDao()!!.insert(mArticleList)
        }
    }
}
