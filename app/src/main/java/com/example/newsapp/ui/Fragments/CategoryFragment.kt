package com.example.newsapp.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.newsapp.Adapters.Categores
import com.example.newsapp.Adapters.CategoryAdapter
import java.text.FieldPosition


class CategoryFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
     lateinit var categoryAdapter:CategoryAdapter
     val listCategory:List<Categores> = listOf(
         Categores(id="sports", "Sports",R.drawable.ball,R.color.red,true),
         Categores(id="entertainment", "Entertainment",R.drawable.politics,R.color.blue,true),
         Categores(id="health", "Health",R.drawable.health,R.color.perple,true),
         Categores(id="business", "Business",R.drawable.bussines,R.color.green,false),
         Categores(id="technology", "Technology",R.drawable.environment,R.color.yellow,false),
         Categores(id="science", "Science",R.drawable.science,R.color.black,false),
     )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    fun initViews(view:View){
     recyclerView=view.findViewById(R.id.categorysRecyclerView)
        categoryAdapter=CategoryAdapter(listCategory)
        categoryAdapter.onItemCategoryClick=object :CategoryAdapter.OnItemCategoryClick{
            override fun onItemCategoryClicked(category: Categores, position: Int) {
               onItemClick?.onItemClick(category,position)
            }
        }
         recyclerView.adapter=categoryAdapter
    }

    var onItemClick:OnItemClick?=null
    interface OnItemClick{
        fun onItemClick(categores: Categores,position: Int)
    }


}