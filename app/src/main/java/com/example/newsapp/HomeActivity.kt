package com.example.newsapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.SearchView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.news.R
import com.example.newsapp.Adapters.Categores
import com.example.newsapp.api.ApiManager
import com.example.newsapp.model.ArticlesResponse
import com.example.newsapp.ui.Fragments.ArticlesFragment
import com.example.newsapp.ui.Fragments.CategoryFragment
import com.example.newsapp.ui.Fragments.SettingsFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    lateinit var drawerLayout:DrawerLayout
    lateinit var iconOpenSM:AppCompatButton
    lateinit var categoryIcon:AppCompatButton
    lateinit var settingIcon:AppCompatButton
   // lateinit var searchView:SearchView
    lateinit var titleText:TextView
    lateinit var iconSearch:ImageView
    val categoryFragment=CategoryFragment()
    val settingsFragment=SettingsFragment()
    val articlesFragment=ArticlesFragment()

//    val flag:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initViews()
        initListeners()
    }

    fun initViews(){

       iconSearch=findViewById(R.id.SearchIconClick)
    //    searchView=findViewById(R.id.search)
        drawerLayout=findViewById(R.id.drawer_layout)
        iconOpenSM=findViewById(R.id.buttonNavOpen)
        categoryIcon=findViewById(R.id.categoryIcon)
        settingIcon=findViewById(R.id.settingsIcon)
        titleText=findViewById(R.id.titleText)
        pushFragment(categoryFragment)

        iconOpenSM.setOnClickListener {
            drawerLayout.open()
        }


    }


    fun initListeners(){

        iconSearch.setOnClickListener {
            val intent = Intent(this, HomeActivitySearch::class.java)
            startActivity(intent)
        }



//        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//
//                ApiManager.getApi().getArticles(ApiManager.apiKey, query = query)
//                    .enqueue(object : Callback<ArticlesResponse> {
//                        override fun onResponse(
//                            call: Call<ArticlesResponse>,
//                            response: Response<ArticlesResponse>
//                        ) {
//                            articlesFragment.articlesAdapter.changeData(response.body()?.articles!!)
//                        }
//
//                        override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
//                            Toast.makeText(this@HomeActivity, "failer", Toast.LENGTH_SHORT).show()
//                        }
//
//                    })
//                return false
//            }


//            override fun onQueryTextChange(newText: String?): Boolean {
//                ApiManager.getApi().getArticles(ApiManager.apiKey, query = newText)
//                    .enqueue(object : Callback<ArticlesResponse> {
//                        override fun onResponse(
//                            call: Call<ArticlesResponse>,
//                            response: Response<ArticlesResponse>
//                        ) {
//                            articlesFragment.articlesAdapter.changeData(response.body()?.articles!!)
//                        }
//
//                        override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
//                            Toast.makeText(this@HomeActivity, "failer", Toast.LENGTH_SHORT).show()
//                        }
//
//                    })
//                return false
//            }
//
//        })


        categoryFragment.onItemClick=object :CategoryFragment.OnItemClick{
            override fun onItemClick(categores: Categores, position: Int) {
                articlesFragment.getTabs(categores.id)
                pushFragment(articlesFragment)
                titleText.text="Articles"
            }

        }


        settingIcon.setOnClickListener {
            pushFragment(settingsFragment)
            drawerLayout.close()
        }


        categoryIcon.setOnClickListener {

            pushFragment(categoryFragment)
            titleText.text="Category"
            drawerLayout.close()
        }


    }


    fun pushFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.contaner,fragment)
            .commit()
    }
    

}