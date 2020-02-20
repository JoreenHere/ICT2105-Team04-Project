package com.ict2105.ict2105_team04_2020.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ict2105.ict2105_team04_2020.DBContract.DBContract
import com.ict2105.ict2105_team04_2020.DBModel.CharityOrganization

class OrganizationsDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Charity.db"

        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBContract.AccountEntry.TABLE_NAME + " (" +
                    DBContract.CharityOrganizationEntry.COLUMN_ORGANIZATION_ID + " INTEGER PRIMARY KEY," +
                    DBContract.CharityOrganizationEntry.COLUMN_ORGANIZATION_NAME + " TEXT," +
                    DBContract.CharityOrganizationEntry.COLUMN_ORGANIZATION_ADDRESS + " TEXT," +
                    DBContract.CharityOrganizationEntry.COLUMN_OPENING_HOURS + " TEXT," +
                    DBContract.CharityOrganizationEntry.COLUMN_LONGITUDE + " TEXT," +
                    DBContract.CharityOrganizationEntry.COLUMN_LATITUDE + " TEXT," +
                    DBContract.CharityOrganizationEntry.COLUMN_ORGANIZATION_IMG + " BLOB)"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.CharityOrganizationEntry.TABLE_NAME
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun insertOrganization(charityOrganization: CharityOrganization) : Long{
        //Get data repository in write mode
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(DBContract.CharityOrganizationEntry.COLUMN_ORGANIZATION_NAME, charityOrganization.organizationName)
            put(DBContract.CharityOrganizationEntry.COLUMN_ORGANIZATION_ADDRESS, charityOrganization.organizationAddress)
            put(DBContract.CharityOrganizationEntry.COLUMN_OPENING_HOURS, charityOrganization.openingHours)
            put(DBContract.CharityOrganizationEntry.COLUMN_LONGITUDE, charityOrganization.longitude)
            put(DBContract.CharityOrganizationEntry.COLUMN_LATITUDE, charityOrganization.latitude)
            put(DBContract.CharityOrganizationEntry.COLUMN_ORGANIZATION_IMG, charityOrganization.organizationImage)
        }

        //Insert into db
        val insertResult = db.insert(DBContract.CharityOrganizationEntry.TABLE_NAME, null, contentValues)

        //Close db connection
        db.close()

        return insertResult
    }
}