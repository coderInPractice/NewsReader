package edu.learn.newsreader.Converter

import androidx.room.TypeConverter
import edu.learn.newsreader.Modals.Source

object SourceConverter {
    @TypeConverter
    fun toText(source: Source): String {
        return source.name
    }
}
