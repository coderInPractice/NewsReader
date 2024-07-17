package edu.learn.newsreader.Modals

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "news_articles")
class Article {
    @Ignore
    @SerializedName("source")
    @Expose
    var source: Source? = null

    @JvmField
    @SerializedName("author")
    @Expose
    var author: String? = null

    @JvmField
    @PrimaryKey
    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @JvmField
    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String? = null

    @JvmField
    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String? = null
}
