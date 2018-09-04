package com.ipsacademypune.user.ipsacademy;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.ipsacademypune.user.ipsacademy.Interface.Api_Ips;
import com.ipsacademypune.user.ipsacademy.Pojo_Clasess.News_pojo;
import com.ipsacademypune.user.ipsacademy.RecyclerView_Adapter.News_adapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Daily_News extends AppCompatActivity {
    RecyclerView recyclerView;
    News_adapter adapter;
    ArrayList<News_pojo> news_list;

    ProgressDialog pd;

    SwipeRefreshLayout mySwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily__news);

        news_list = new ArrayList<>();

        //refresh layout
        mySwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        System.out.println("Refresh called");

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        loadData();
                        mySwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(Daily_News.this, "Page refresh..", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadData();

    }

    public void loadData() {
//Web Services to fetch all news.........................................................................................................
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(Api_Ips.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api_Ips Ad = retrofit1.create(Api_Ips.class);
        Call<ArrayList<News_pojo>> call1 = Ad.fetchAllNews();

        pd = new ProgressDialog(Daily_News.this);
        pd.setMessage("Checking Latest News!");
        pd.show();

        call1.enqueue(new Callback<ArrayList<News_pojo>>() {
            @Override
            public void onResponse(Call<ArrayList<News_pojo>> call1, Response<ArrayList<News_pojo>> response) {
                pd.dismiss();

//                System.out.println("server response fetching doctors///////////////////" + response.code());
//                System.out.println("server response///////////////////" + response.isSuccessful());
                news_list.clear();
                ArrayList<News_pojo> allNews = response.body();
                for (News_pojo news : allNews) {
                    news_list.add(news);
                }

                adapter = new News_adapter(Daily_News.this, news_list);
                recyclerView.setAdapter(adapter);

                mySwipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<ArrayList<News_pojo>> call, Throwable t) {
                pd.dismiss();

                System.out.println("error :" + t.getMessage());
                Toast.makeText(Daily_News.this, "Server error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
