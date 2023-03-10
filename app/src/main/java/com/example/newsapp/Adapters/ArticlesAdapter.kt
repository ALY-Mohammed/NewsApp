package com.example.newsapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.newsapp.model.ArticlesItem
import com.example.newsapp.model.ArticlesResponse

class ArticlesAdapter(var items:List<ArticlesItem?>):Adapter<ArticlesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview_articles,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int { return items.size }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article= items[position]
        holder.articlesTextView.text=article?.description
        holder.author.text=article?.author
        holder.DateArticles.text=article?.publishedAt
        val url =article!!.url

        Glide.with(holder.imageView)
            .load(url)
            .into(holder.imageView)

        holder.item.setOnClickListener {
            onItemClick?.onItemClicked( article!!, position)
        }

    }



    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val imageView=itemView.findViewById<ImageView>(R.id.articlesImage)
        val author=itemView.findViewById<TextView>(R.id.autherTextView)
        val articlesTextView=itemView.findViewById<TextView>(R.id.articlesTextView)
        val DateArticles=itemView.findViewById<TextView>(R.id.dateTextViwe)
        val item =itemView.findViewById<ConstraintLayout>(R.id.item)
    }


    fun changeData(newList:List<ArticlesItem?>){
        items=newList
        notifyDataSetChanged()
    }


    var onItemClick:OnItemClick?=null

    interface OnItemClick{
        fun onItemClicked(article:ArticlesItem ,position: Int)
    }
}