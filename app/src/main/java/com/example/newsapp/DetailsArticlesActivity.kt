package com.example.newsapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.example.news.R

class DetailsArticlesActivity : AppCompatActivity() {
    lateinit var titleText:TextView
    lateinit var imageView: ImageView
    lateinit var auther:TextView
    lateinit var description:TextView
    lateinit var date:TextView
    lateinit var content:TextView
    lateinit var link:TextView
    lateinit var backArr:AppCompatButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_articles)
        initViews()

        val id  = intent.getStringExtra("id")
        val dateText  =  intent.getStringExtra("date")
        val image  =   intent.getStringExtra("image")
        val authorText  =   intent.getStringExtra("author")
        val descText  =  intent.getStringExtra("desc")
        val contentText  =  intent.getStringExtra("content")
        val url  = intent.getStringExtra("url")

        titleText.text= id
        date.text=dateText
        Glide.with(imageView)
            .load(image)
            .into(imageView)
        auther.text=authorText
        description.text=descText
        content.text=contentText
        link.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
            startActivity(intent);
        }

    }
    fun initViews(){

        titleText=findViewById(R.id.titleText)
        imageView=findViewById(R.id.articlesImage)
        auther=findViewById(R.id.autherTextView)
        description=findViewById(R.id.articlesTextView)
        date=findViewById(R.id.dateTextViwe)
        content=findViewById(R.id.description)
        link=findViewById(R.id.link)
        backArr=findViewById(R.id.buttonBackArr)

        backArr.setOnClickListener {
            finish()
        }

    }


}

