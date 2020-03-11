package com.lordsam.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.Toast

class DatabaseManager{
    val dbName = "ItemBase"
    val tableName = "Items"
    val dbVersion = 1
    val colId = "Id"
    val colItem = "Item"
    var sqlDB :SQLiteDatabase? = null
    val createSQLTable = "CREATE TABLE IF NOT EXISTS $tableName ($colId INTEGER PRIMARY KEY, $colItem TEXT);"

    constructor(context: Context){
        val db = DatabaseHelper(context)
        sqlDB = db.writableDatabase
    }

    inner class DatabaseHelper :SQLiteOpenHelper {

        private var context : Context? = null

        constructor(context : Context) :super(context, dbName, null, dbVersion){
            this.context = context
        }

        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(createSQLTable)
            Toast.makeText(this.context, "Database Created", Toast.LENGTH_SHORT).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS $tableName")
        }

    }

    fun insert(values :ContentValues) :Long{
        return sqlDB!!.insert(tableName, "", values)
    }

    fun query(projection :Array<String>, selection :String,selectionArgs :Array<String>, sortOrder :String) :Cursor{

        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = tableName
        return queryBuilder.query(sqlDB, projection, selection,selectionArgs, null, null, sortOrder)
    }

    fun delete(selection :String,selectionArgs :Array<String>) :Int{
         return sqlDB!!.delete(tableName, selection, selectionArgs)
    }
}