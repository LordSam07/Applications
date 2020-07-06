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

    fun readData() :MutableList<User>{

        var list :MutableList<User> = ArrayList()
        var db = this.readableDatabase
        val query = "SELECT * FROM $DATABASE_TABLE"
        val result =  db.rawQuery(query, null)

        if (result.moveToFirst()){
            do {
                val user = User()
                user.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                user.userName = result.getString(result.getColumnIndex(COL_USER_NAME))
                user.password = result.getString(result.getColumnIndex(COL_PASSWORD))
                list.add(user)
            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    fun updateData() {

        var db = this.writableDatabase
        val query = "SELECT * FROM $DATABASE_TABLE"
        val result =  db.rawQuery(query, null)

        if (result.moveToFirst()){
            do {
                val cv = ContentValues()
                cv.put(COL_PASSWORD, /*result.getInt(result.getColumnIndex(COL_PASSWORD))*/ "Updated")
                db.update(DATABASE_TABLE, cv, "$COL_ID =? AND $COL_USER_NAME =?",
                    arrayOf(result.getString(result.getColumnIndex(COL_ID)),
                    result.getString(result.getColumnIndex(COL_USER_NAME))))

            }while (result.moveToNext())
        }

        result.close()
        db.close()
    }

    fun deleteData(){
        val db = this.writableDatabase
        db.delete(DATABASE_TABLE, null, null)//to delete all values
        //db.delete(DATABASE_TABLE, "$COL_ID=?", arrayOf(1.toString()))
        db.close()
    }



}