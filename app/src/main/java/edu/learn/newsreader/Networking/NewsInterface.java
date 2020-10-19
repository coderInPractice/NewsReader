package edu.learn.newsreader.Networking;

import edu.learn.newsreader.Modals.NewsStatus;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsInterface {

    @GET("top-headlines")
    Call<NewsStatus> getNews(

            @Query("country") String country ,
            @Query("apiKey") String apiKey

    );
}
