package edu.learn.newsreader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import edu.learn.newsreader.modals.Article
import edu.learn.newsreader.viewModel.NewsViewModel


class MainActivity : AppCompatActivity() {

    companion object {
        private const val API_KEY: String = "ADD_LATEST_API_KEY"
        private const val COUNTRY = "in"
    }

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewsAdapter


    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout = findViewById(R.id.swipe_container)
        recyclerView = findViewById(R.id.news_recyclerView)
        recyclerView.setLayoutManager(LinearLayoutManager(this))

        adapter = NewsAdapter(this)

        swipeRefreshLayout.setOnRefreshListener {
            newsViewModel.startApiCall(COUNTRY, API_KEY, swipeRefreshLayout)
            loadNewsArticles()
        }


        newsViewModel = AndroidViewModelFactory.getInstance(application).create<NewsViewModel>(
            NewsViewModel::class.java
        )

        loadNewsArticles()
    }

    private fun loadNewsArticles() {
        newsViewModel.allNews.observe(this) { articles: List<Article> ->
            adapter.addList(articles)
            recyclerView.adapter = adapter
        }
    }
}
