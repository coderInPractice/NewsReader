package edu.learn.newsreader

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import edu.learn.newsreader.modals.Article
import edu.learn.newsreader.NewsAdapter.NewsViewHolder
import edu.learn.newsreader.utils.Utils.dateFormat
import edu.learn.newsreader.utils.Utils.dateToTimeFormat

class NewsAdapter(private var mContext: Context) : RecyclerView.Adapter<NewsViewHolder>() {
    private var mArticleList: List<Article> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.news_item_list, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val mArticle = mArticleList[position]

        Glide
            .with(mContext)
            .load(mArticle.urlToImage)
            .centerCrop()
            .into(holder.newsImg)

        holder.author.text = mArticle.author
        holder.title.text = mArticle.title
        holder.publishedAt.text = dateFormat(mArticle.publishedAt)
        holder.time.text = dateToTimeFormat(mArticle.publishedAt)

        //holder.source.setText(mArticle.getSource().getName());
    }

    override fun getItemCount(): Int {
        return mArticleList.size
    }

    fun addList(articleList: List<Article>) {
        mArticleList = articleList
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(itemView: View) : ViewHolder(itemView) {
        var newsImg: ImageView = itemView.findViewById(R.id.news_img)
        var author: TextView = itemView.findViewById(R.id.news_author)
        var publishedAt: TextView = itemView.findViewById(R.id.publishedAt)
        var title: TextView = itemView.findViewById(R.id.news_title)
        var source: TextView = itemView.findViewById(R.id.source)
        var time: TextView = itemView.findViewById(R.id.time)
    }
}
