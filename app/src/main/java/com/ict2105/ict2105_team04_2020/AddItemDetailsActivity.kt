package com.ict2105.ict2105_team04_2020

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ict2105.ict2105_team04_2020.DBHelper.ListingsDBHelper

class AddItemDetailsActivity : AppCompatActivity() {

    private lateinit var listingsDBHelper: ListingsDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item_details)
        supportActionBar?.title = "Add Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val btnListIt = findViewById<Button>(R.id.btnListIt)

        listingsDBHelper = ListingsDBHelper(this)

    }
}
