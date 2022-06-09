package com.example.trip_planner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_market_category.*

class MarketCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_category)

        var places = ArrayList<String>()

        var totalPoint: Int = 0
        var counter: Int = 0

        var db = DataBaseHelper(this)
        var data = db.readData()

        val actionBar = supportActionBar
        //Başlık Eklemeyi Sağlar
        actionBar!!.title = "Marketler"

        for(i in 0 until data.size){
            var temp:String = data.get(i).placeName

            if (places.isEmpty()){
                places.add(temp)
            }
            else if(places.indexOf(temp) == -1){
                places.add(temp)
            }
        }

        for (i in 0..3){
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

        cardMarket1.setOnClickListener {
            val intent = Intent(this, MarketAvcilar::class.java)
            startActivity(intent)
        }
        cardMarket2.setOnClickListener {
            val intent = Intent(this, MarketGoztepe::class.java)
            startActivity(intent)
        }
        cardMarket3.setOnClickListener {
            val intent = Intent(this, MarketSariyer::class.java)
            startActivity(intent)
        }
        cardMarket4.setOnClickListener {
            val intent = Intent(this, MarketTuzla::class.java)
            startActivity(intent)
        }
    }
    fun writePoint(totalPoint:Int, counter:Int,card:String){

        var rate:Int = totalPoint / counter
        rate.toFloat()

        if(card == "Carrefour SA Avcılar"){
            marketPuan1.text = "Puan : "+ rate.toString()
        }
        if(card == "Mopaş Göztepe"){
            marketPuan2.text = "Puan : "+ rate.toString()
        }
        if(card == "Migros Sarıyer"){
            marketPuan3.text = "Puan : "+ rate.toString()
        }
        if(card == "File Tuzla"){
            marketPuan4.text = "Puan : "+ rate.toString()
        }
    }
}