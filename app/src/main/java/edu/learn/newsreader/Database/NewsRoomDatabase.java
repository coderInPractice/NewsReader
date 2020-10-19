package edu.learn.newsreader.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DatabaseModal.class},version = 1,exportSchema = false)
public abstract class NewsRoomDatabase extends RoomDatabase {

    private static NewsRoomDatabase instance;

    public abstract NewsDao newsDao();

    public static synchronized NewsRoomDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,NewsRoomDatabase.class,"news_db").build();
        }
        return instance;
    }
}
