package edu.learn.newsreader.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.learn.newsreader.Converter.SourceConverter
import edu.learn.newsreader.Modals.Article
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(
    SourceConverter::class
)
abstract class NewsRoomDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao?

    companion object {
        private var instance: NewsRoomDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        @JvmField
        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        @JvmStatic
        @Synchronized
        fun getInstance(context: Context?): NewsRoomDatabase? {
            if (instance == null) {
                instance =
                    databaseBuilder(context!!, NewsRoomDatabase::class.java, "news_db").build()
            }
            return instance
        }
    }
}
