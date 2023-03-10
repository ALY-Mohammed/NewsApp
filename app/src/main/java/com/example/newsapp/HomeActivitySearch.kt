package com.example.newsapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.SearchView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.newsapp.Adapters.ArticlesAdapter
import com.example.newsapp.Adapters.Categores
import com.example.newsapp.api.ApiManager
import com.example.newsapp.model.ArticlesItem
import com.example.newsapp.model.ArticlesResponse
import com.example.newsapp.ui.Fragments.ArticlesFragment
import com.example.newsapp.ui.Fragments.CategoryFragment
import com.example.newsapp.ui.Fragments.SettingsFragment
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivitySearch : AppCompatActivity() {
    lateinit var drawerLayout:DrawerLayout
    lateinit var iconOpenSM:AppCompatButton
    lateinit var categoryIcon:AppCompatButton
    lateinit var settingIcon:AppCompatButton
    lateinit var searchView:SearchView
    val articlesFragment=ArticlesFragment()
    var id = ""

   // lateinit var tabLayout: TabLayout
    var articlesAdapter = ArticlesAdapter(listOf())
    lateinit var articleRecyclerView: RecyclerView
    lateinit var title :String
    var page: Int = 1
    val pageSize = 20


//    val flag:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initViews()
        initListeners()
    }

    fun initViews(){

    //    searchIconClick=findViewById(R.id.SearchIconClick)
        searchView=findViewById(R.id.search)
        drawerLayout=findViewById(R.id.drawer_layout)
        iconOpenSM=findViewById(R.id.buttonNavOpen)
        categoryIcon=findViewById(R.id.categoryIcon)
        settingIcon=findViewById(R.id.settingsIcon)
        pushFragment(articlesFragment)

        iconOpenSM.setOnClickListener {
            finish()
        }


    }


    fun initListeners(){

//        articlesFragment.articlesAdapter.onItemClick = object : ArticlesAdapter.OnItemClick {
//            override fun onItemClicked(article: ArticlesItem, position: Int) {
//                id = article.title.toString()
//                val intent = Intent(this@HomeActivitySearch, DetailsArticlesActivity::class.java)
//                intent.putExtra("id", title)
//                intent.putExtra("date", article.publishedAt)
//                intent.putExtra("image", article.urlToImage)
//                intent.putExtra("author", article.author)
//                intent.putExtra("desc", article.description)
//                intent.putExtra("content", article.content)
//                intent.putExtra("url", article.url)
//                startActivity(intent)
//
//            }
//        }
//        searchIconClick.setOnClickListener {
//            val intent = Intent(this, SearchHomeActivity::class.java)
//            startActivity(intent)
//        }



        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                ApiManager.getApi().getArticles(ApiManager.apiKey, query = query)
                    .enqueue(object : Callback<ArticlesResponse> {
                        override fun onResponse(
                            call: Call<ArticlesResponse>,
                            response: Response<ArticlesResponse>
                        ) {
                            articlesFragment.articlesAdapter.changeData(response.body()?.articles!!)
                        }

                        override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                            Toast.makeText(this@HomeActivitySearch, "failer", Toast.LENGTH_SHORT).show()
                        }

                    })
                return false
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                ApiManager.getApi().getArticles(ApiManager.apiKey, query = newText)
                    .enqueue(object : Callback<ArticlesResponse> {
                        override fun onResponse(
                            call: Call<ArticlesResponse>,
                            response: Response<ArticlesResponse>
                        ) {
                            articlesFragment.articlesAdapter.changeData(response.body()?.articles!!)
                        }

                        override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                            Toast.makeText(this@HomeActivitySearch, "failer", Toast.LENGTH_SHORT).show()
                        }

                    })
                return false
            }

        })




    }


    fun pushFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.contaner,fragment)
            .commit()
    }
    

}