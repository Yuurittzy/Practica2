package com.example.practica2_yuritzy.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.practica2_yuritzy.model.Box

class DBBoxes(context: Context?) : DBHelper(context) {

    val context = context

    fun insertGame(name: String, quantity: String, price: String): Long{
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase
        var id: Long = 0

        try{
            val values = ContentValues()

            values.put("name", name)
            values.put("quantity", quantity)
            values.put("price", price)

            id = db.insert(TABLE_BOXES, null, values)

        }catch(e: Exception){
            //Manejo de la excepción
        }finally {
            db.close()
        }

        return id
    }

   fun getBoxes(): ArrayList<Box> {
        val dbHelper = DBHelper(context)
       val db = dbHelper.writableDatabase

       var listGames = ArrayList<Box>()
       var gameTmp: Box? = null
       var cursorGames: Cursor? = null

       cursorGames = db.rawQuery("SELECT * FROM $TABLE_BOXES", null)

       if(cursorGames.moveToFirst()){
           do{
               gameTmp = Box(cursorGames.getLong(0), cursorGames.getString(1), cursorGames.getString(2), cursorGames.getString(3))
               listGames.add(gameTmp)
           }while(cursorGames.moveToNext())
       }

       cursorGames.close()

       return listGames
    }

}