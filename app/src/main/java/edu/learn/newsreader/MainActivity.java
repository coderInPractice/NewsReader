package edu.learn.newsreader;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private final String URL = "https://newsapi.org/v2/top-headlines?country=in&apiKey='your api key'";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<NewsList> myNewsList;

    private androidx.swiperefreshlayout.widget.SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipeContainer);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        recyclerView = findViewById(R.id.news_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myNewsList = new ArrayList<>();

        loadData();

    }

    private void loadData(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        AsyncHttpClient client  = new AsyncHttpClient();
        client.get(URL,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                Log.i("SUCCESS JSON",response.toString());
                progressDialog.dismiss();

                try {
                    JSONArray jsonArray = response.getJSONArray("articles");

                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        NewsList newsList = new NewsList(jsonObject.getString("title"), jsonObject.getString("publishedAt"), jsonObject.getString("urlToImage"),jsonObject.getString("url"));
                        myNewsList.add(newsList);
                    }
                    adapter = new NewsAdapter(getApplicationContext(),myNewsList);
                    recyclerView.setAdapter(adapter);
                    swipeRefreshLayout.setRefreshing(false); //we call setRefreshing(false) to signal refresh has finished


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response){

            }
        });
    }

}
