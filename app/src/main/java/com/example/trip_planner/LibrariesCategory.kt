package com.example.trip_planner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_libraries_category.*

class LibrariesCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libraries_category)

        var places = ArrayList<String>()

        val actionBar = supportActionBar
        //Başlık Eklemeyi Sağlar
        actionBar!!.title = "Kütüphaneler"

        var totalPoint:Int = 0
        var counter:Int = 0

        var db  = DataBaseHelper(this)
        var data = db.readData()

        for(i in 0 until data.size){
            var temp:String = data.get(i).placeName

            if (places.isEmpty()){
                places.add(temp)
            }
            else if(places.indexOf(temp) == -1){
                places.add(temp)
            }
        }

        for (i in 0..4){
            for(j in 0 until data.size){
                if(data.get(j).placeName == places[i] && data.size > 0) {
                    totalPoint = totalPoint + data.get(j).point
                    counter++
                }
            }
            writePoint(totalPoint, counter, places[i])
            totalPoint = 0
            counter = 0
        }

        cardLib1.setOnClickListener {
            val intent = Intent(this,KAtaturk::class.java)
            startActivity(intent)
        }
        cardLib2.setOnClickListener {
            val intent = Intent(this,KBeyazit::class.java)
            startActivity(intent)
        }
        cardLib3.setOnClickListener {
            val intent = Intent(this,KErsoy::class.java)
            startActivity(intent)
        }
        cardLib4.setOnClickListener {
            val intent = Intent(this,KOrhanKemal::class.java)
            startActivity(intent)
        }
        cardLib5.setOnClickListener {
            val intent = Intent(this,KTurabiBaba::class.java)
            startActivity(intent)
        }
    }
    fun writePoint(totalPoint:Int, counter:Int,card:String){

       var rate:Int = totalPoint / counter
       rate.toFloat()

       if(card == "Atatürk Kütüphanesi"){
           libPuan1.text = "Puan : "+ rate.toString()
       }
       if(card == "Beyazıt Kütüphanesi"){
           libPuan2.text = "Puan : "+ rate.toString()
       }
       if(card == "Mehmet Akif Ersoy Kütüphanesi"){
           libPuan3.text = "Puan : "+ rate.toString()
       }
       if(card == "Orhan Kemal Kütüphanesi"){
           libPuan4.text = "Puan : "+ rate.toString()
       }
       if(card == "Turabi Baba Kütüphanesi"){
           libPuan5.text = "Puan : "+ rate.toString()
       }
   }
}