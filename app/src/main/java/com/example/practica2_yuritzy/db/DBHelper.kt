package com.example.practica2_yuritzy.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class DBHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("CREATE TABLE $TABLE_BOXES (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, quantity TEXT NOT NULL, price TEXT NOT NULL)")
    }
    //id INTEGER PRIMARY KEY AUTOINCREMENT,

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE $TABLE_BOXES")
        onCreate(p0)
    }

    //private static final int SALUDO = 1  -> Así se manejan los statics en java

    //Así se manejan los statics en Kotlin
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "databoxes.db"
        public const val TABLE_BOXES = "boxes"
    }
}
