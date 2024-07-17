package edu.learn.newsreader.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.learn.newsreader.Converter.SourceConverter;
import edu.learn.newsreader.Modals.Article;

@Database(entities = {Article.class},version = 1,exportSchema = false)
@TypeConverters(SourceConverter.class)
public abstract class NewsRoomDatabase extends RoomDatabase {

    private static NewsRoomDatabase instance;
    private static final int NUMBER_OF_THREADS = 4 ;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public abstract NewsDao newsDao();

    public static synchronized NewsRoomDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,NewsRoomDatabase.class,"news_db").build();
        }
        return instance;
    }
}
