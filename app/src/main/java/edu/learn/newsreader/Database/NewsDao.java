package edu.learn.newsreader.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import edu.learn.newsreader.Modals.Article;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Article> newsArticles);

    @Query("SELECT * FROM news_articles")
    LiveData<List<Article>> getAllNews();

    @Query("DELETE FROM news_articles")
    void deleteAllNews();
}
