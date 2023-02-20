package com.example.newsapp.api

import com.example.newsapp.model.ArticlesResponse
import com.example.newsapp.model.TabsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WepServices {


    @GET("/v2/top-headlines/sources")
    fun getTabs(
        @Query("apiKey") apiKey:String,
    ):Call<TabsResponse>

    @GET("/v2/everything")
    fun getArticles(
        @Query("apiKey") apiKey:String,
        @Query("sources") tab:String
    ):Call<ArticlesResponse>

}