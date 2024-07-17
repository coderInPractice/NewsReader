package edu.learn.newsreader.modals

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsStatus {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("totalResult")
    @Expose
    var totalResult: Int = 0

    @JvmField
    @SerializedName("articles")
    @Expose
    var article: List<Article>? = null
}
