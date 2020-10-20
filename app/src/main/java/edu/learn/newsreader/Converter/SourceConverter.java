package edu.learn.newsreader.Converter;

import androidx.room.TypeConverter;

import edu.learn.newsreader.Modals.Source;

public class SourceConverter {

    @TypeConverter
    public static String toText(Source source){
        return source.getName();
    }
}
