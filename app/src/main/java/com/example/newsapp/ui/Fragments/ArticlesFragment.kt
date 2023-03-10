package com.example.newsapp.ui.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.newsapp.Adapters.ArticlesAdapter
import com.example.newsapp.Adapters.Categores
import com.example.newsapp.DetailsArticlesActivity
import com.example.newsapp.api.ApiManager
import com.example.newsapp.model.ArticlesItem
import com.example.newsapp.model.ArticlesResponse
import com.example.newsapp.model.TabDM
import com.example.newsapp.model.TabsResponse
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ArticlesFragment() : Fragment() {

    lateinit var tabLayout: TabLayout
    var articlesAdapter = ArticlesAdapter(listOf())
    lateinit var articleRecyclerView: RecyclerView
   lateinit var title :String
    var page: Int = 1
    val pageSize = 20
    var id = ""
 //  var isloading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initListeners()
    }

    fun initViews(view: View) {

        articlesAdapter.onItemClick = object : ArticlesAdapter.OnItemClick {
            override fun onItemClicked(article: ArticlesItem, position: Int) {

                id = article.title.toString()
                val intent = Intent(requireContext(), DetailsArticlesActivity::class.java)
                intent.putExtra("date",article.publishedAt)
                intent.putExtra("image",article.urlToImage)
                intent.putExtra("author",article.author)
                intent.putExtra("desc",article.description)
                intent.putExtra("content",article.content)
                intent.putExtra("url",article.url)
                startActivity(intent)
            }


        }

        tabLayout = view.findViewById(R.id.tabs_items)
        articleRecyclerView = view.findViewById(R.id.tabs_content_recyclerView)
        articleRecyclerView.adapter = articlesAdapter


//        articleRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val layoutManager= articleRecyclerView.layoutManager as LinearLayoutManager
//                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
//                val total=layoutManager.itemCount
//                val visible= 3
//                if (!isloading &&total-lastVisibleItemPosition <= visible)
//                {
//                    isloading=true
//                    page++
//                    getArticles(id)
//                }
//            }
//
//        })



    }


    fun showTabs(tabs: List<TabDM?>) {
        tabs.forEach { tab ->
            val newTabe = tabLayout.newTab()
            newTabe.text = tab?.name
            newTabe.tag = tab?.id ?: ""
            tabLayout.addTab(newTabe)
        }
    }


    fun getTabs(id: String) {

        ApiManager.getApi().getTabs(ApiManager.apiKey,id).enqueue(object : Callback<TabsResponse> {
            override fun onResponse(call: Call<TabsResponse>, response: Response<TabsResponse>) {

                if (response.body()?.code == null) {
                    title=id
                    showTabs(response.body()?.tabs!!)
                }

            }

            override fun onFailure(call: Call<TabsResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Failure", Toast.LENGTH_SHORT).show()
            }

        })

    }


    fun initListeners() {



        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                var id = tab?.tag!! as String
                getArticles(id)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                var id = tab?.tag!! as String
                getArticles(id)
            }


        })

    }

    fun getArticles(id:String){

        ApiManager.getApi().getArticles(ApiManager.apiKey,id, page = page , pageSize = pageSize ).enqueue(object :Callback<ArticlesResponse>{
            override fun onResponse(
                call: Call<ArticlesResponse>,
                response: Response<ArticlesResponse>
            )
            {
                articlesAdapter.changeData(response.body()?.articles!!)
            }

            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                Log.e("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA","Failure")
            }

        })

//        isloading=false

    }


}