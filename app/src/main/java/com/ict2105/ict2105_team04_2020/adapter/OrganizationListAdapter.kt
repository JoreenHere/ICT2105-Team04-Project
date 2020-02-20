package com.ict2105.ict2105_team04_2020.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ict2105.ict2105_team04_2020.R
import com.ict2105.ict2105_team04_2020.ui.charityOrganization.CharityOrganizationFragment
import kotlinx.android.synthetic.main.organization_list_item.view.*

class OrganizationListAdapter (private val organizationList : ArrayList<String>, private val context: Context): RecyclerView.Adapter<OrganizationListAdapter.OrganizationViewHolder>(){

    class OrganizationViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view), View.OnClickListener{
        val organizationName: TextView = view.textViewOrganizationName

        init{
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val intent = Intent(context, CharityOrganizationFragment::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrganizationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.organization_list_item, parent, false)
        return OrganizationViewHolder(view, context)
    }

    override fun getItemCount() = organizationList.size

    override fun onBindViewHolder(holder: OrganizationViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.organizationName.text = organizationList[position]
    }

}