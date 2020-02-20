package com.ict2105.ict2105_team04_2020.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.ict2105.ict2105_team04_2020.DBContract.DBContract
import com.ict2105.ict2105_team04_2020.DBModel.Account
import com.ict2105.ict2105_team04_2020.DBModel.Listing

class ListingsDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    /* Enable Foreign Key Support */
    override fun onConfigure(db: SQLiteDatabase?) {
        super.onConfigure(db)
        db?.setForeignKeyConstraintsEnabled(true)
    }

    companion object{
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Charity.db"

        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBContract.ListingEntry.TABLE_NAME + " (" +
                    DBContract.ListingEntry.COLUMN_LISTING_ID + " INTEGER PRIMARY KEY," +
                    DBContract.ListingEntry.COLUMN_ACCOUNT_ID + " INTEGER" +
                    DBContract.ListingEntry.COLUMN_ITEM_CONDITION + " TEXT," +
                    DBContract.ListingEntry.COLUMN_ITEM_NAME + " TEXT," +
                    DBContract.ListingEntry.COLUMN_ITEM_DETAILS + " TEXT," +
                    DBContract.ListingEntry.COLUMN_LOCATION + " TEXT," +
                    DBContract.ListingEntry.COLUMN_CATEGORY + " TEXT," +
                    DBContract.ListingEntry.COLUMN_ITEM_IMAGE1 + " BLOB," +
                    DBContract.ListingEntry.COLUMN_ITEM_IMAGE2 + " BLOB," +
                    DBContract.ListingEntry.COLUMN_ITEM_IMAGE3 + " BLOB," +
                    "FOREIGN KEY(" + DBContract.ListingEntry.COLUMN_ACCOUNT_ID + ") REFERENCES" +
                    DBContract.AccountEntry.TABLE_NAME + "(" + DBContract.AccountEntry.COLUMN_ACCOUNT_ID + "))"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.ListingEntry.TABLE_NAME
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    /* Retrieve all listings */
    fun selectAllListing() : ArrayList<Listing>{
        val listings = ArrayList<Listing>()
        val db = this.writableDatabase
        var cursor: Cursor?
        try{
            cursor = db.rawQuery("SELECT" +
                    DBContract.ListingEntry.COLUMN_ACCOUNT_ID + ", " +
                    DBContract.ListingEntry.COLUMN_ITEM_NAME + ", " +
                    DBContract.AccountEntry.COLUMN_USERNAME + ", " +
                    DBContract.AccountEntry.COLUMN_PROFILE_IMG + ", " +
                    "FROM " + DBContract.ListingEntry.TABLE_NAME +
                    "INNER JOIN " + DBContract.AccountEntry.TABLE_NAME + " ON" +
                    DBContract.ListingEntry.COLUMN_ACCOUNT_ID + " = " + DBContract.AccountEntry.COLUMN_ACCOUNT_ID, null)
        }catch (e: SQLiteException){
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var itemName: String
        var username: String
        var profileImage: ByteArray

        if(cursor!!.moveToFirst()){
            while (!cursor.isAfterLast){
                itemName = cursor.getString(cursor.getColumnIndex(DBContract.ListingEntry.COLUMN_ITEM_NAME))


               // listings.add(Listing(itemName, itemCondition, itemDetails, location, itemImage1, itemImage2, itemImage3))
                cursor.moveToNext()
            }
        }

        return listings
    }

    /* Retrieve listing by id for MeFragment */
    fun selectListingById(id: Int) : ArrayList<Listing>{
        val listings = ArrayList<Listing>()
        val db = this.writableDatabase
        var cursor: Cursor?
        try{
            cursor = db.rawQuery("SELECT * FROM " + DBContract.AccountEntry.TABLE_NAME +
                    " WHERE " + DBContract.ListingEntry.COLUMN_LISTING_ID + " = '" + id + "'", null)
        }catch (e: SQLiteException){
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var itemName: String
        var itemCondition: String
        var itemDetails: String
        var location: String
        var itemImage1: ByteArray
        var itemImage2: ByteArray
        var itemImage3: ByteArray

        if(cursor!!.moveToFirst()){
            while (!cursor.isAfterLast){
                itemName = cursor.getString(cursor.getColumnIndex(DBContract.ListingEntry.COLUMN_ITEM_NAME))
                itemCondition = cursor.getString(cursor.getColumnIndex(DBContract.ListingEntry.COLUMN_ITEM_CONDITION))
                itemDetails = cursor.getString(cursor.getColumnIndex(DBContract.ListingEntry.COLUMN_ITEM_DETAILS))
                location = cursor.getString(cursor.getColumnIndex(DBContract.ListingEntry.COLUMN_LOCATION))
                itemImage1 = cursor.getBlob(cursor.getColumnIndex(DBContract.ListingEntry.COLUMN_ITEM_IMAGE1))
                itemImage2 = cursor.getBlob(cursor.getColumnIndex(DBContract.ListingEntry.COLUMN_ITEM_IMAGE2))
                itemImage3 = cursor.getBlob(cursor.getColumnIndex(DBContract.ListingEntry.COLUMN_ITEM_IMAGE3))

                //listings.add(Listing(itemName, itemCondition, itemDetails, location, itemImage1, itemImage2, itemImage3))
                cursor.moveToNext()
            }
        }

        return listings
    }

    /* Insert new listing from AddNewListingActivity */
    fun insertListing(listing: Listing) : Long{
        //Get date repository in write mode
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(DBContract.ListingEntry.COLUMN_ITEM_NAME, listing.itemName)
            put(DBContract.ListingEntry.COLUMN_ITEM_DETAILS, listing.itemDetails)
            put(DBContract.ListingEntry.COLUMN_ITEM_CONDITION, listing.itemCondition)
            put(DBContract.ListingEntry.COLUMN_LOCATION, listing.location)
            put(DBContract.ListingEntry.COLUMN_CATEGORY, listing.itemCategory)
            put(DBContract.ListingEntry.COLUMN_ITEM_IMAGE1, listing.itemImage1)
            put(DBContract.ListingEntry.COLUMN_ITEM_IMAGE2, listing.itemImage2)
            put(DBContract.ListingEntry.COLUMN_ITEM_IMAGE3,listing.itemImage3)

        }

        //Insert into db
        val insertResult = db.insert(DBContract.ListingEntry.TABLE_NAME, null, contentValues)

        //Close db connection
        db.close()

        return insertResult
    }


}