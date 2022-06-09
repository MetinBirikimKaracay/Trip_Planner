package com.example.trip_planner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_hotels_category.*
import kotlinx.android.synthetic.main.activity_market_category.*
import kotlinx.android.synthetic.main.activity_otopark_category.*

class OtoparkCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var places = ArrayList<String>()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otopark_category)

        var totalPoint: Int = 0
        var counter: Int = 0

        var db = DataBaseHelper(this)
        var data = db.readData()

        val actionBar = supportActionBar
        //Başlık Eklemeyi Sağlar
        actionBar!!.title = "Otoparklar"

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

        cardOtopark1.setOnClickListener {
            val intent = Intent(this, OtoparkBasaksehir::class.java)
            startActivity(intent)
        }
        cardOtopark2.setOnClickListener {
            val intent = Intent(this, OtoparkCihangir::class.java)
            startActivity(intent)
        }
        cardOtopark3.setOnClickListener {
            val intent = Intent(this, OtoparkEsenler::class.java)
            startActivity(intent)
        }
        cardOtopark4.setOnClickListener {
            val intent = Intent(this, OtoparkUskudar::class.java)
            startActivity(intent)
        }
    }
    fun writePoint(totalPoint:Int, counter:Int,card:String) {

        var rate: Int = totalPoint / counter
        rate.toFloat()

        if (card == "Otopark Bahçeşehir") {
            otoparkPuan1.text = "Puan : " + rate.toString()
        }
        if (card == "Otopark Cihangir") {
            otoparkPuan2.text = "Puan : " + rate.toString()
        }
        if (card == "Otopark Esenler") {
            otoparkPuan3.text = "Puan : " + rate.toString()
        }
        if (card == "Otopark Üsküdar") {
            otoparkPuan4.text = "Puan : " + rate.toString()
        }
    }
}