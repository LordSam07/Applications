package com.lordsam.databuddy

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

var DATABASE_NAME = "ProfileDB"
var DATABASE_TABLE = "Users"
var COL_USER_NAME = "UserName"
var COL_PASSWORD = "Password"
var COL_ID = "id"


class DatabaseHandlerUserInfo(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $DATABASE_TABLE " +
                "($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_USER_NAME VARCHAR(256), " +
                "$COL_PASSWORD VARCHAR(256));"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(user :User){
        val db = this.writableDatabase
        var contentValues = ContentValues()
        contentValues.put(COL_USER_NAME, user.userName)
        contentValues.put(COL_PASSWORD,user.password)
        var rowID = db.insert(DATABASE_TABLE, null, contentValues)

        if (rowID == (-1).toLong()){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()

        }
    }

}