package com.ict2105.ict2105_team04_2020

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import com.ict2105.ict2105_team04_2020.DBHelper.AccountsDBHelper

class MainActivity : AppCompatActivity() {

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

                if(userType == "User"){
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
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
            message = "Incorrect username and password"
        }else{
            var isValidLogin = accountsDBHelper.isLoginValid(username, password)

            if(!isValidLogin){
                message = "Incorrect username and password"
            }
        }

        return message
    }
}
