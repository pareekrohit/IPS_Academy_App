package com.ipsacademypune.user.ipsacademy.RecyclerView_Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ipsacademypune.user.ipsacademy.Pojo_Clasess.News_pojo;
import com.ipsacademypune.user.ipsacademy.R;

import java.util.ArrayList;
import java.util.List;

public class News_adapter extends RecyclerView.Adapter<News_adapter.NewsViewHolder> {

    private Context mCtx;
    private ArrayList<News_pojo> news_List;

    public News_adapter(Context mCtx, ArrayList<News_pojo> news_List) {
        this.mCtx = mCtx;
        this.news_List = news_List;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items_daily_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        News_pojo DD = news_List.get(position);

        //BASE64 String decode and display in news image
        String base64String = "data:image/jpg;base64," + DD.getNews_image();
        final String pureBase64Encoded = base64String.split(",")[1];
        final byte[] decodedBytes = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

        Glide.with(mCtx).load(decodedBytes).asBitmap().placeholder(R.drawable.defult_image).into(holder.img_news);
//        holder.img_news.setImageBitmap(decodedBitmap);
        holder.txt_date.setText(DD.getInsert_date());
        holder.txt_title.setText(DD.getNews_title());
        holder.txt_decription.setText(DD.getNews_description());

    }

    @Override
    public int getItemCount() {
        return news_List.size();
    }


    class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView img_news;
        TextView txt_title, txt_decription, txt_date;

        public NewsViewHolder(View itemView) {
            super(itemView);
            img_news = itemView.findViewById(R.id.img_news);
            txt_title = itemView.findViewById(R.id.txt_news_title);
            txt_decription = itemView.findViewById(R.id.txt_news_description);
            txt_date = itemView.findViewById(R.id.txt_news_date);

        }
    }

}
