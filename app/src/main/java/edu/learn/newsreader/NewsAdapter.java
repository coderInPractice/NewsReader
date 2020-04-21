package edu.learn.newsreader;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private final Context mContext;
    private final List<NewsList> mNews;

    public NewsAdapter(Context mContext, List<NewsList> mNews) {
        this.mContext = mContext;
        this.mNews = mNews;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final NewsList newsList = mNews.get(position);
        holder.heading.setText(newsList.getHeading());
        holder.timeStamp.setText(newsList.getTimeStamp());
        holder.more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Button clicked","worked?");
                NewsList newsList1 = mNews.get(position);
                String URL = newsList1.geturl();
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(v.getContext(), Uri.parse(URL));
//                Intent intent = new Intent(v.getContext(),News_Launcher.class);
//                intent.putExtra("url",newsList1.geturl());
//                v.getContext().startActivity(intent);
//                Log.i("Intent","Intent Fired");
            }
        });
        Picasso.get()
                .load(newsList.getImg_url())
                .into(holder.img_thumb);
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView heading;
        public TextView timeStamp;
        public ImageView img_thumb;
        public Button more_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.news_title);
            timeStamp = itemView.findViewById(R.id.published_time);
            img_thumb = itemView.findViewById(R.id.news_img);
            more_btn = itemView.findViewById(R.id.more_btn);
        }
    }

}
