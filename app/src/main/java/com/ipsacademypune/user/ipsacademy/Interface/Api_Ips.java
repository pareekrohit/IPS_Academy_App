package com.ipsacademypune.user.ipsacademy.Interface;

import com.ipsacademypune.user.ipsacademy.Pojo_Clasess.News_pojo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Hp on 03/04/2018.
 */

public interface Api_Ips {

    String BASE_URL="http://192.168.0.9:8081/ipsAcadamy/";

    @POST("all_news_api.php")
    Call<ArrayList<News_pojo>> fetchAllNews();

}
