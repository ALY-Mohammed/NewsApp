package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.forEach
import androidx.core.view.get
import com.example.news.R
import com.example.newsapp.api.ApiManager
import com.example.newsapp.model.ArticlesResponse
import com.example.newsapp.model.TabDM
import com.example.newsapp.model.TabsResponse
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Objects

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_article)
        initViews()
        initListeners()
        getTabs()
    }

  fun  initViews(){
      tabLayout=findViewById(R.id.tabs_items)
    }


    fun initListeners(){

        tabLayout.addOnTabSelectedListener(object :OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
              val id =tab?.tag.toString()
                getArticles(id)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val id =tab?.tag.toString()
                getArticles(id)
            }


        })

    }




    fun showTabs(tabs:List<TabDM?>){
        tabs.forEach {tab->
            val newTabe=tabLayout.newTab()
            newTabe.text=tab?.name
            newTabe.tag=tab?.id?:""
            tabLayout.addTab(newTabe)
        }
    }

    fun getTabs(){

        ApiManager.getApi().getTabs(ApiManager.apiKey).enqueue(object :Callback<TabsResponse>{
            override fun onResponse(call: Call<TabsResponse>, response: Response<TabsResponse>) {

                if (response.body()?.code==null) {
                    showTabs(response.body()?.tabs!!)
                }

            }

            override fun onFailure(call: Call<TabsResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failure", Toast.LENGTH_SHORT).show()
            }

        })

    }



    fun getArticles(id:String){

        ApiManager.getApi().getArticles(ApiManager.apiKey,id).enqueue(object :Callback<ArticlesResponse>{
            override fun onResponse(
                call: Call<ArticlesResponse>,
                response: Response<ArticlesResponse>
            )
            {
                Log.e("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA","${response.body()}")
            }

            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                Log.e("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA","Failure")
            }

        })

    }
}