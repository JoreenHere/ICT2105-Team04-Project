package com.ict2105.ict2105_team04_2020

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputLayout
import com.ict2105.ict2105_team04_2020.DBHelper.AccountsDBHelper

class MainActivity : AppCompatActivity() {

    private val sharedPrefFile = "Account"
    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor

    private var accountsDBHelper: AccountsDBHelper = AccountsDBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        /* TextView */
        val textViewErrorMsg = findViewById<TextView>(R.id.textViewErrorMsg)

        /* EditText */
        val editTextUsername = findViewById<EditText>(R.id.editTextUsername)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)

        /* Button */
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnLogin.setOnClickListener {
            /* Store user input into variables */
            var username = editTextUsername.text.toString()
            var password = editTextPassword.text.toString()

            /* Get error message from validation */
            var errorMsg = validateLogin(username, password)

            /* Set error message */
            textViewErrorMsg.text = errorMsg

            /* If login is valid, check for userType */
            if(errorMsg.isEmpty()){
                var userType = accountsDBHelper.checkUserType(username, password)

                sharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
                editor =  sharedPreferences.edit()
                editor.putString("username", username)
                editor.putString("userType", userType)

                editor.apply()
                editor.commit()

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }


        }

        btnRegister.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateLogin(username: String, password: String) : String{
        var message = ""

        if(username.isEmpty() || password.isEmpty()){
            message = "Incorrect username or password"
        }else{
            var isValidLogin = accountsDBHelper.isLoginValid(username, password)

            if(!isValidLogin){
                message = "Incorrect username or password"
            }
        }

        return message
    }
}
