package edu.learn.newsreader;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.learn.newsreader.Modals.Article;
import edu.learn.newsreader.ViewModel.NewsViewModel;


public class MainActivity extends AppCompatActivity {

    private final String API_KEY = "6e7723902d8a4851a894d9065525802e";
    private  final String COUNTRY = "in";

    private NewsViewModel newsViewModel;
    private RecyclerView recyclerView;
    private NewsAdapter adapter;


    //private androidx.swiperefreshlayout.widget.SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.news_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new NewsAdapter(this);


        newsViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(NewsViewModel.class);

        loadNewsArticles(COUNTRY,API_KEY);

    }

    private void loadNewsArticles(String country, String api_key) {

        newsViewModel.startApiCall(country,api_key);
        newsViewModel.getAllNews().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                adapter.addList(articles);
                recyclerView.setAdapter(adapter);
            }
        });
    }


}
