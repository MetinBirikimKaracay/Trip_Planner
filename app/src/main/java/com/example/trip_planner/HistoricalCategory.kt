package com.example.trip_planner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_historical_category.*

class HistoricalCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historical_category)

        var places = ArrayList<String>()

        var totalPoint:Int = 0
        var counter:Int = 0

        var db  = DataBaseHelper(this)
        var data = db.readData()

        val actionBar = supportActionBar
        //Başlık Eklemeyi Sağlar
        actionBar!!.title = "Tarihi Yerler"

        for(i in 0 until data.size){
            var temp:String = data.get(i).placeName

            if (places.isEmpty()){
                places.add(temp)
            }
            else if(places.indexOf(temp) == -1){
                places.add(temp)
            }
        }

        for (i in 0..5){
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

        cardHistory1.setOnClickListener {
            val intent = Intent(this,TYAyasofya::class.java)
            startActivity(intent)
        }

        cardHistory2.setOnClickListener {
            val intent = Intent(this,TYGalata::class.java)
            startActivity(intent)
        }

        cardHistory3.setOnClickListener {
            val intent = Intent(this,TYKapaliCarsi::class.java)
            startActivity(intent)
        }
        cardHistory4.setOnClickListener {
            val intent = Intent(this,TYSultanAhmet::class.java)
            startActivity(intent)
        }
        cardHistory5.setOnClickListener {
            val intent = Intent(this, TYTopkapi::class.java)
            startActivity(intent)
        }

        cardHistory6.setOnClickListener {
            val intent = Intent(this, TYYerebatan::class.java)
            startActivity(intent)
        }
    }

    fun writePoint(totalPoint:Int, counter:Int,card:String){

       var rate:Int = totalPoint / counter
       rate.toFloat()

       if(card == "Ayasofya"){
           historyPuan1.text = "Puan : "+ rate.toString()
       }
       if(card == "Galata Kulesi"){
           historyPuan2.text = "Puan : "+ rate.toString()
       }
       if(card == "Kapalı Çarşı"){
           historyPuan3.text = "Puan : "+ rate.toString()
       }
       if(card == "Sultan Ahmet Camii"){
           historyPuan4.text = "Puan : "+ rate.toString()
       }
       if(card == "Topkapı Sarayı"){
           historyPuan5.text = "Puan : "+ rate.toString()
       }
       if(card == "Yerebatan Sarnıcı"){
           historyPuan6.text = "Puan : "+ rate.toString()
       }
    }
}