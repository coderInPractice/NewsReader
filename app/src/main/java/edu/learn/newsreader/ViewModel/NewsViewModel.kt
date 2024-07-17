package edu.learn.newsreader.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import edu.learn.newsreader.Modals.Article
import edu.learn.newsreader.Repository.NewsRepos

class NewsViewModel(application: Application) : AndroidViewModel(application) {
    val allNews: LiveData<List<Article>>
    private val newsRepos = NewsRepos(application)

    init {
        allNews = newsRepos.allNews
    }

    fun startApiCall(country: String?, apiKey: String?, swipeRefreshLayout: SwipeRefreshLayout?) {
        newsRepos.getNewsFromApi(country, apiKey, swipeRefreshLayout!!)
    }
}
