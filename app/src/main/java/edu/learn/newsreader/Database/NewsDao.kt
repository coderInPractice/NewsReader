package edu.learn.newsreader.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.learn.newsreader.Modals.Article

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(newsArticles: List<Article?>?)

    @get:Query("SELECT * FROM news_articles")
    val allNews: LiveData<List<Article?>?>?

    @Query("DELETE FROM news_articles")
    fun deleteAllNews()
}
