package com.example.trip_planner

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val database_name = "Deneme"
val table_name = "Yorumlar"
var col_id = "id"
var col_name = "Name"
// String ifadeleri düzelt mesela Description ı Soyad yap, placeName i Mekan ismi yap, image yerine puan yaz
var col_surName = "Description"
var col_placeName = "CatName"
var col_comment = "Comment"
var col_point = "Image"

class DataBaseHelper (var context: Context): SQLiteOpenHelper(context,
    database_name,
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        //veritabanı oluştuğunda bir kez çalışır

        var createTable1 = " CREATE TABLE " + table_name + "(" +
                col_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                col_name + " VARCHAR(256)," +
                col_surName + " VARCHAR(256)," +
                col_placeName + " VARCHAR(256)," +
                col_comment + " VARCHAR(256)," +
                col_point + " INTEGER)"

        db?.execSQL(createTable1)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(comments: Comments){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(col_name, comments.name)
        cv.put(col_surName, comments.surName)
        cv.put(col_placeName, comments.placeName)
        cv.put(col_comment, comments.comment)
        cv.put(col_point, comments.point)

        var sonuc = db.insert(table_name, null, cv)
        if(sonuc == (-1).toLong()){
            Toast.makeText(context, "Hatalı", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context, "Başarılı", Toast.LENGTH_LONG).show()
        }
    }

    fun readData():MutableList<Comments> {
        var liste:MutableList<Comments> = ArrayList()
        val db = this.readableDatabase
        var query = "Select * from "+ table_name+" ORDER BY "+ col_id+" DESC"
        var result = db.rawQuery(query,null)

        if(result.moveToFirst()) {
            do {
                var comments = Comments()
                comments.id = result.getString(result.getColumnIndexOrThrow(col_id)).toInt()
                comments.name = result.getString(result.getColumnIndexOrThrow(col_name))
                comments.surName = result.getString(result.getColumnIndexOrThrow(col_surName))
                comments.comment = result.getString(result.getColumnIndexOrThrow(col_comment))
                comments.placeName = result.getString(result.getColumnIndexOrThrow(col_placeName))
                comments.point = result.getString(result.getColumnIndexOrThrow(col_point)).toInt()

                liste.add(comments)

            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return liste
    }

}








