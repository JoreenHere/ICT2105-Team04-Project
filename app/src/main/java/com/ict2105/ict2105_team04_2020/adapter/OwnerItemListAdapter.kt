package com.ict2105.ict2105_team04_2020.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ict2105.ict2105_team04_2020.R
import kotlinx.android.synthetic.main.item_list_card.view.*

class OwnerItemListAdapter (private val itemList : ArrayList<String>, private val context: Context): RecyclerView.Adapter<OwnerItemListAdapter.ItemViewHolder>(){

    class ItemViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view){
        val itemName: TextView = view.textViewItemName

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.owner_item_list_card, parent, false)
        return ItemViewHolder(view, context)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.itemName.text = itemList[position]
    }

}