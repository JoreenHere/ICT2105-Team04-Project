package com.ict2105.ict2105_team04_2020.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.os.strictmode.SqliteObjectLeakedViolation
import android.util.Log
import com.ict2105.ict2105_team04_2020.DBContract.DBContract
import com.ict2105.ict2105_team04_2020.DBModel.Account

class AccountsDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Charity.db"

        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBContract.AccountEntry.TABLE_NAME + " (" +
                    DBContract.AccountEntry.COLUMN_ACCOUNT_ID + " INTEGER PRIMARY KEY," +
                    DBContract.AccountEntry.COLUMN_USERNAME + " TEXT," +
                    DBContract.AccountEntry.COLUMN_PASSWORD + " TEXT," +
                    DBContract.AccountEntry.COLUMN_BIO + " TEXT," +
                    DBContract.AccountEntry.COLUMN_EMAIL + " TEXT," +
                    DBContract.AccountEntry.COLUMN_PHONE_NUMBER + " TEXT," +
                    DBContract.AccountEntry.COLUMN_RATING + " FLOAT," +
                    DBContract.AccountEntry.COLUMN_PROFILE_IMG + " BLOB," +
                    DBContract.AccountEntry.COLUMN_USER_TYPE + " TEXT)"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.AccountEntry.TABLE_NAME

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    /* Check if username exist */
    fun isUsernameExist(username: String) : Boolean{
        var result = false
        val db = this.writableDatabase
        var cursor: Cursor? = null

        try{
            cursor = db.rawQuery("SELECT * FROM " + DBContract.AccountEntry.TABLE_NAME +
                    " WHERE " + DBContract.AccountEntry.COLUMN_USERNAME + " = '" + username + "'", null)
        }catch (e: SQLiteException){
            db.execSQL(SQL_CREATE_ENTRIES)
            result = true
        }

        if(cursor!!.count > 0){
            result = true
        }

        return result
    }

    /* Check for valid login */
    fun isLoginValid(username:String, password: String) : Boolean{
        var result = true
        val db = this.writableDatabase
        var cursor: Cursor? = null

        try{
            cursor = db.rawQuery("SELECT * FROM " + DBContract.AccountEntry.TABLE_NAME +
                    " WHERE " + DBContract.AccountEntry.COLUMN_USERNAME + " = '" + username + "' AND " +
                    DBContract.AccountEntry.COLUMN_PASSWORD + " = '" + password + "'", null)
        }catch (e: SQLiteException){
            db.execSQL(SQL_CREATE_ENTRIES)
            result = false
        }

        if(cursor!!.count == 1){
            result = true

        }else if(cursor!!.count == 0 || cursor!!.count > 1){
            result = false
        }

        return result
    }

    /* Check for UserType - User or Volunteer */
    fun checkUserType(username: String, password: String) : String {
        val db = this.writableDatabase
        var cursor: Cursor? = null

        try{
            cursor = db.rawQuery("SELECT " + DBContract.AccountEntry.COLUMN_USER_TYPE + " FROM " + DBContract.AccountEntry.TABLE_NAME +
                    " WHERE " + DBContract.AccountEntry.COLUMN_USERNAME + " = '" + username + "' AND " +
                    DBContract.AccountEntry.COLUMN_PASSWORD + " = '" + password + "'", null)
        }catch (e: SQLiteException){
            db.execSQL(SQL_CREATE_ENTRIES)
        }

        var userType = ""

        if(cursor!!.moveToFirst()){
            userType = cursor.getString(cursor.getColumnIndex(DBContract.AccountEntry.COLUMN_USER_TYPE))
        }

        return userType
    }

    /* Retrieve accounts by username for MeFragment*/
    fun selectAccountByUsername(username: String) : ArrayList<Account>{
        val accounts = ArrayList<Account>()
        val db = this.writableDatabase
        var cursor: Cursor?
        try{
            cursor = db.rawQuery("SELECT * FROM " + DBContract.AccountEntry.TABLE_NAME +
                    " WHERE " + DBContract.AccountEntry.COLUMN_USERNAME + " = '" + username + "'", null)

        }catch (e: SQLiteException){
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var name: String
        var bio: String
        var email: String
        var phoneNumber: String
        var rating: Float
        var profileImage: ByteArray

        if(cursor!!.moveToFirst()){
            while(!cursor.isAfterLast){
                name = cursor.getString(cursor.getColumnIndex(DBContract.AccountEntry.COLUMN_USERNAME))
                bio = cursor.getString(cursor.getColumnIndex(DBContract.AccountEntry.COLUMN_BIO))
                email = cursor.getString(cursor.getColumnIndex(DBContract.AccountEntry.COLUMN_EMAIL))
                phoneNumber = cursor.getString(cursor.getColumnIndex(DBContract.AccountEntry.COLUMN_PHONE_NUMBER))
                rating = cursor.getFloat(cursor.getColumnIndex(DBContract.AccountEntry.COLUMN_RATING))
                profileImage = cursor.getBlob(cursor.getColumnIndex(DBContract.AccountEntry.COLUMN_PROFILE_IMG))

                accounts.add(Account(name, bio, email, phoneNumber, rating, profileImage))
                cursor.moveToNext()
            }
        }

        return accounts
    }

    /* Insert new account from RegisterActivity */
    fun insertAccount(account: Account) : Long{
        //Get data repository in write mode
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(DBContract.AccountEntry.COLUMN_USERNAME, account.username)
            put(DBContract.AccountEntry.COLUMN_PASSWORD, account.password)
            put(DBContract.AccountEntry.COLUMN_BIO, account.bio)
            put(DBContract.AccountEntry.COLUMN_EMAIL, account.email)
            put(DBContract.AccountEntry.COLUMN_PHONE_NUMBER, account.phoneNumber)
            put(DBContract.AccountEntry.COLUMN_RATING, account.rating)
            put(DBContract.AccountEntry.COLUMN_PROFILE_IMG, account.profileImage)
            put(DBContract.AccountEntry.COLUMN_USER_TYPE, account.userType)
        }

        //Insert into db
        val insertResult = db.insert(DBContract.AccountEntry.TABLE_NAME, null, contentValues)

        //Close db connection
        db.close()

        return insertResult
    }

    /* Update account from EditProfileActivity */
    fun updateAccount(account: com.ict2105.ict2105_team04_2020.DBModel.Account): Int{
        //Get data repository in write mode
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(DBContract.AccountEntry.COLUMN_USERNAME, account.username)
            put(DBContract.AccountEntry.COLUMN_PASSWORD, account.password)
            put(DBContract.AccountEntry.COLUMN_BIO, account.bio)
            put(DBContract.AccountEntry.COLUMN_EMAIL, account.email)
            put(DBContract.AccountEntry.COLUMN_PHONE_NUMBER, account.phoneNumber)
            put(DBContract.AccountEntry.COLUMN_RATING, account.rating)
            put(DBContract.AccountEntry.COLUMN_PROFILE_IMG, account.profileImage)
        }

        //Update row into db
        val updateResult = db.update(DBContract.AccountEntry.TABLE_NAME, contentValues, "username="+account.username, null)

        //Close db connection
        db.close()

        return updateResult
    }

}