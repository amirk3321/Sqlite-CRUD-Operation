package com.sqlite.sqlitedatabase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.sqlite.sqlitedatabase.model.User

class SqliteHelper : SQLiteOpenHelper{
    constructor(context: Context) : super(context, "student.db", null, 1)

    private lateinit var db : SQLiteDatabase
    private val ID="id"
    private val NAME="name"
    private val EMAIL="email"
    private val PASSWORD="password"
    private val Table="studentTable"
    private val Create_table="create table $Table ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $NAME TEXT NOT NULL,$EMAIL TEXT NOT NULL UNIQUE,$PASSWORD TEXT NOT NULL)"
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(Create_table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $Table")
        //onCreate(db)
    }
    fun onGetData():Cursor{
        db=this.readableDatabase
        val cursor=db.rawQuery("SELECT * FROM $Table",null)
        return cursor
    }
    fun onInsertData(user :User)  {
        db=this.writableDatabase
        db.insert(Table,null,ContentValues().apply {
            put(NAME,user.name)
            put(EMAIL,user.email)
            put(PASSWORD,user.password)
        })

    }
    fun onUpdateData(Id : String,user : User){
        db=this.writableDatabase
        db.update(Table,ContentValues().apply {
            put(ID,Id)
            put(NAME,user.name)
            put(EMAIL,user.email)
            put(PASSWORD,user.password)
        },"id =?", arrayOf(Id))
    }
    fun onDelete(Id : String){
        db=this.writableDatabase
        db.delete(Table,"id =?", arrayOf(Id))
    }
}