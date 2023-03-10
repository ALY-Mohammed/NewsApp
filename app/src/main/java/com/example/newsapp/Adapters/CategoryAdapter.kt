package com.example.newsapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.news.R
import com.google.android.material.card.MaterialCardView
import java.util.Locale.Category

class CategoryAdapter(val items : List<Categores>):Adapter<CategoryAdapter.ViewHolder>(){


    override fun getItemViewType(position: Int): Int {
        val category=items.get(position)
        return if (category.isLeftSided)
            1
        else
            2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view =LayoutInflater.from(parent.context)
           .inflate(
               if (viewType==1)
                   R.layout.item_left_recyclerview_category
               else
                   R.layout.item_right_recyclerview_category
               ,parent,false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val materialCardView=holder.itemView as MaterialCardView
        val category = items.get(position)
        holder.imageView.setImageResource(category.imageId)
        holder.textView.text=category.title
        materialCardView.setCardBackgroundColor(holder.itemView.context.getColor(category.colorId))
        holder.imageView.setOnClickListener {
            onItemCategoryClick?.onItemCategoryClicked(category,position)
        }

    }

    override fun getItemCount(): Int { return items.size }



    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
       val imageView:ImageView=itemView.findViewById(R.id.imageCategoryRecyclerView)
        val textView:TextView=itemView.findViewById(R.id.textCategoryRecyclerView)
     //   val materialCardView=itemView.findViewById<MaterialCardView>(R.id.itemView)
    }


    var onItemCategoryClick:OnItemCategoryClick?=null

    interface OnItemCategoryClick{
        fun onItemCategoryClicked(category: Categores,position: Int)
    }
}