package edu.learn.newsreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import edu.learn.newsreader.Modals.Article;
import edu.learn.newsreader.Utils.Utils;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    Context mContext;
    private List<Article> mArticleList = new ArrayList<>();

    public NewsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_item_list,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article mArticle = mArticleList.get(position);

        Glide
                .with(mContext)
                .load(mArticle.getUrlToImage())
                .centerCrop()
                .into(holder.news_img);

        holder.author.setText(mArticle.getAuthor());
        holder.title.setText(mArticle.getTitle());
        holder.publishedAt.setText(Utils.DateFormat(mArticle.getPublishedAt()));
        holder.time.setText(Utils.DateToTimeFormat(mArticle.getPublishedAt()));
        //holder.source.setText(mArticle.getSource().getName());

    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }
    public  void addList(List<Article> articleList){
        mArticleList = articleList;
        notifyDataSetChanged();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView news_img;
        TextView author,publishedAt,title,source,time;
            public NewsViewHolder(@NonNull View itemView) {
                super(itemView);
                news_img = itemView.findViewById(R.id.news_img);
                author = itemView.findViewById(R.id.news_author);
                publishedAt = itemView.findViewById(R.id.publishedAt);
                title = itemView.findViewById(R.id.news_title);
                source = itemView.findViewById(R.id.source);
                time = itemView.findViewById(R.id.time);

            }
        }

}
