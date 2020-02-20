package com.ict2105.ict2105_team04_2020

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ict2105.ict2105_team04_2020.adapter.CategoryListAdapter

class AddNewListingActivity : AppCompatActivity() {

    private lateinit var recyclerViewCategoryList: RecyclerView
    private lateinit var categoryListAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val categoryList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_listing)
        supportActionBar?.title = "Choose a category"

        //Set Actionbar Back Button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        categoryList.add("Clothes")
        categoryList.add("Shoes")
        categoryList.add("Bags")
        categoryList.add("Books")
        categoryList.add("Furniture")
        categoryList.add("Electronic Devices")

        categoryListAdapter = CategoryListAdapter(categoryList, this)
        viewManager = LinearLayoutManager(this)

        recyclerViewCategoryList = findViewById<RecyclerView>(R.id.recyclerViewCategoryList).apply {
            adapter = categoryListAdapter
            layoutManager = viewManager

        }
    }
}
