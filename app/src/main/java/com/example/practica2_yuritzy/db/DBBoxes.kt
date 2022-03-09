package com.example.practica2_yuritzy.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.practica2_yuritzy.model.Box

class DBBoxes(context: Context?) : DBHelper(context) {

    val context = context

    fun insertBox(name: String, quantity: String, price: String): Long{
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
       var cursorBoxes: Cursor? = null

       cursorBoxes = db.rawQuery("SELECT * FROM $TABLE_BOXES", null)

       if(cursorBoxes.moveToFirst()){
           do{
               gameTmp = Box(cursorBoxes.getLong(0), cursorBoxes.getString(1), cursorBoxes.getString(2), cursorBoxes.getString(3))
               listGames.add(gameTmp)
           }while(cursorBoxes.moveToNext())
       }

       cursorBoxes.close()

       return listGames
    }

    fun getBox(id: Int): Box?{
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        var game: Box? = null
        var cursorBoxes: Cursor? = null

        cursorBoxes = db.rawQuery("SELECT * FROM $TABLE_BOXES WHERE id = $id LIMIT 1", null)

        if(cursorBoxes.moveToFirst()){
            game = Box(cursorBoxes.getLong(0), cursorBoxes.getString(1), cursorBoxes.getString(2), cursorBoxes.getString(3))
        }

        cursorBoxes.close()

        return game
    }

    fun updateBox(id: Int, name: String, quantity: String, price: String): Boolean{
        var banderaCorrecto = false

        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        try{
            db.execSQL("UPDATE $TABLE_BOXES SET name = '$name', quantity = '$quantity', price = '$price' WHERE id = $id")
            banderaCorrecto = true
        }catch(e: Exception){

        }finally {
            db.close()
        }

        return banderaCorrecto

    }

    fun deleteBox(id: Int): Boolean{
        var banderaCorrecto = true

        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        try{
            db.execSQL("DELETE FROM $TABLE_BOXES WHERE id = $id")
            banderaCorrecto = true
        }catch(e: Exception){

        }finally {
            db.close()
        }

        return banderaCorrecto
    }

}