package com.ict2105.ict2105_team04_2020

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.core.text.isDigitsOnly
import com.google.android.material.textfield.TextInputLayout
import com.ict2105.ict2105_team04_2020.DBHelper.AccountsDBHelper
import com.ict2105.ict2105_team04_2020.DBModel.Account
import kotlinx.android.synthetic.main.activity_add_item_details.*
import java.io.ByteArrayOutputStream


class RegisterActivity : AppCompatActivity() {

    private var accountsDBHelper: AccountsDBHelper = AccountsDBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        /* TextInputLayout */
        val layoutUsername = findViewById<TextInputLayout>(R.id.layout_username)
        val layoutPassword = findViewById<TextInputLayout>(R.id.layout_password)
        val layoutConfirmPassword = findViewById<TextInputLayout>(R.id.layout_confirmPassword)
        val layoutEmail = findViewById<TextInputLayout>(R.id.layout_email)
        val layoutPhoneNumber = findViewById<TextInputLayout>(R.id.layout_phoneNumber)

        /* EditText */
        val editTextUsername = findViewById<EditText>(R.id.editTextUsername)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val editTextConfirmPassword = findViewById<EditText>(R.id.editTextConfirmPassword)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPhoneNumber = findViewById<EditText>(R.id.editTextPhoneNumber)

        /* Button */
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val btnBack = findViewById<Button>(R.id.btnBack)

        btnRegister.setOnClickListener {

            /* Store user input into variables */
            var username = editTextUsername.text.toString()
            var password = editTextPassword.text.toString()
            var confirmPassword = editTextConfirmPassword.text.toString()
            var email = editTextEmail.text.toString()
            var phoneNumber = editTextPhoneNumber.text.toString()
            val bio = "This is my bio"

            /* Get error message from validation */
            var usernameMessage = validateUsername(username)
            var emailMessage = validateEmail(email)
            var phoneNumberMessage = validatePhoneNumber(phoneNumber)
            var passwordMessage = validatePassword(password)
            var confirmPasswordMessage = validateConfirmPassword(password, confirmPassword)

            /* Set error message */
            layoutUsername.error = usernameMessage
            layoutEmail.error = emailMessage
            layoutPhoneNumber.error = phoneNumberMessage
            layoutPassword.error = passwordMessage
            layoutConfirmPassword.error = confirmPasswordMessage

            /* If all inputs are valid, insert into DB */
            if(usernameMessage.isEmpty() && emailMessage.isEmpty() && phoneNumberMessage.isEmpty() && passwordMessage.isEmpty() && confirmPasswordMessage.isEmpty()){
                val account = Account()
                account.username = username
                account.password = password
                account.bio = bio
                account.email = email
                account.phoneNumber = phoneNumber
                account.rating = 0.0f
                account.profileImage = convertBitMapToByteArray()
                account.userType = "User"

                accountsDBHelper.insertAccount(account)
            }

        }

        btnBack.setOnClickListener {
            super.onBackPressed()
        }

    }

    /* Convert Drawable to ByteArray */
    private fun convertBitMapToByteArray() : ByteArray{
        val bitmap = AppCompatResources.getDrawable(this, R.drawable.ic_person_black_24dp)?.toBitmap()
        val bos = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, bos)
        return bos.toByteArray()
    }

    /* Check if username is valid */
    private fun validateUsername(username: String) : String{
        var message = ""

        if(username.isEmpty()){
            message = "This field is required"
        }else{
            var isUsernameExist = accountsDBHelper.isUsernameExist(username)
            if(isUsernameExist){
                message = "Username already exist"
            }
        }
        return message
    }

    /* Check if password is valid */
    private fun validatePassword(password: String) : String{
        var message = ""

        if(password.length < 8){
            message = "Must be at least 8 characters long"
        }

        return message
    }

    /* Check if confirm password is valid */
    private fun validateConfirmPassword(password:String, confirmPassword: String) : String{
        var message = ""

        if(confirmPassword.isEmpty()){
            message = "This field is required"
        }else if(confirmPassword != password){
            message = "Confirm Password does not match"
        }

        return message
    }

    /* Check if email is valid */
    private fun validateEmail(email: String) : String{
        var message = ""

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            message = "Invalid email format"
        }

        return message
    }

    /* Check if phone number is valid */
    private fun validatePhoneNumber(phoneNumber: String) : String{
        var message = ""

        if(phoneNumber.length != 8){
            message = "Invalid Phone Number"

        }else if(!phoneNumber.isDigitsOnly()){
            message = "Must be numbers only"

        }else{
            var firstNum = phoneNumber.substring(0, 1)

            if(firstNum != "8" && firstNum != "9"){
                message = "Must start with 9 or 8"

            }
        }

        return message
    }
}
