package com.example.newsapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {


    companion object{

        val apiKey ="c1996d746b014ed1a943b961d2b1cd07"
        private var retrofit:Retrofit?=null

        private fun getInstance():Retrofit{
            if (retrofit==null){
                retrofit=Retrofit.Builder()
                    .baseUrl("https://newsapi.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit !!
        }

        fun getApi ():WepServices{
            return getInstance().create(WepServices::class.java)
        }
    }
}