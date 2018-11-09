package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.datafrominternet.R;
import com.example.rkjc.news_app_2.NewsItem;

import java.util.ArrayList;


public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsHolder> {
    Context mContext;
    ArrayList<NewsItem> mNews;

    public NewsRecyclerViewAdapter(Context context, ArrayList<NewsItem> news){
        this.mContext = context;
        this.mNews = news;
    }

    @Override
    public NewsRecyclerViewAdapter.NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.news_item, parent, shouldAttachToParentImmediately);  //potential break with news_item
        NewsHolder viewHolder = new NewsHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsRecyclerViewAdapter.NewsHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView author;
        TextView url;
        TextView title;
        TextView description;
        //TextView image;
       // TextView publishDate;

        public NewsHolder(View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.author);
            title = (TextView) itemView.findViewById(R.id.title);
            url = (TextView) itemView.findViewById(R.id.url);
            //description = (TextView) itemView.findViewById(R.id.description);
            //image = (TextView) itemView.findViewById(R.id.image);
            //publishDate = (TextView) itemView.findViewById(R.id.publishDate);
        }

        void bind(final int listIndex) {
            author.setText(mNews.get(listIndex).getAuthor());
            url.setText(mNews.get(listIndex).getUrl());
            title.setText(mNews.get(listIndex).getTitle());
            //description.setText(mNews.get(listIndex).getDescription());
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String urlString = mNews.get(getAdapterPosition()).getUrl();
            Intent intent = new Intent(mContext, WebActivity.class); //implementation of WebActivity?
            intent.putExtra("urlString", urlString);
            mContext.startActivity(intent);
        }
    }

}
