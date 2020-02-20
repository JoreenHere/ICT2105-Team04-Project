package com.ict2105.ict2105_team04_2020.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ict2105.ict2105_team04_2020.AddItemDetailsActivity
import com.ict2105.ict2105_team04_2020.R
import kotlinx.android.synthetic.main.category_list_item.view.*

class CategoryListAdapter (private val categoryList : ArrayList<String>, private val context: Context): RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>(){

    class CategoryViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view), View.OnClickListener{
        val category: TextView = view.textViewCategory

        init{
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val intent = Intent(context, AddItemDetailsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_list_item, parent, false)
        return CategoryViewHolder(view, context)
    }

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.category.text = categoryList[position]
    }

}