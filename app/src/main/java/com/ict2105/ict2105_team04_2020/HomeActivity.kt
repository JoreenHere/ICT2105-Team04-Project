package com.ict2105.ict2105_team04_2020

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {

    private val sharedPrefFile = "Account"
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val fBtnAddListing: FloatingActionButton = findViewById(R.id.fbtnAddListing)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_browse,
                R.id.navigation_category,
                R.id.navigation_organization,
                R.id.navigation_me
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        fBtnAddListing.setOnClickListener {
            val intent = Intent(this, AddNewListingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //Get UserType from sharedPreferences
        sharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        var userType = sharedPreferences.getString("userType", "").toString()

        // Inflate appbar menu base on userType
        if(userType == "User"){
            menuInflater.inflate(R.menu.appbar_menu, menu)
        }else{
            menuInflater.inflate(R.menu.volunteer_appbar_menu, menu)
        }

        return true
    }
}
