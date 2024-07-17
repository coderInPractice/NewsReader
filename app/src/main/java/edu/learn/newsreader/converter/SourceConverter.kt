package edu.learn.newsreader.converter

import androidx.room.TypeConverter
import edu.learn.newsreader.modals.Source

object SourceConverter {
    @TypeConverter
    fun toText(source: Source): String {
        return source.name!!
    }
}
