package com.example.trip_planner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ibadethane_category.*

class IbadethaneCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ibadethane_category)

        var places = ArrayList<String>()

        var totalPoint:Int = 0
        var counter:Int = 0

        var db  = DataBaseHelper(this)
        var data = db.readData()

        val actionBar = supportActionBar
        //Başlık Eklemeyi Sağlar
        actionBar!!.title = "İbadethaneler"

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

        cardIbadet1.setOnClickListener {
            val intent = Intent(this,IbadetAyaYorgi::class.java)
            startActivity(intent)
        }
        cardIbadet2.setOnClickListener {
            val intent = Intent(this,IbadetMihrimah::class.java)
            startActivity(intent)
        }
        cardIbadet3.setOnClickListener {
            val intent = Intent(this,IbadetSinagog::class.java)
            startActivity(intent)
        }
        cardIbadet4.setOnClickListener {
            val intent = Intent(this,IbadetYusa::class.java)
            startActivity(intent)
        }
        cardIbadet5.setOnClickListener {
            val intent = Intent(this,IbadetSuleymaniye::class.java)
            startActivity(intent)
        }
        cardIbadet6.setOnClickListener {
            val intent = Intent(this,IbadetKilise::class.java)
            startActivity(intent)
        }
    }
    fun writePoint(totalPoint:Int, counter:Int,card:String){

        var rate:Int = totalPoint / counter
        rate.toFloat()

        if(card == "Aya Yorgi Patrikhane Kilisesi"){
            ibadetPuan1.text = "Puan : "+ rate.toString()
        }
        if(card == "Mihrimah Sultan Camii"){
            ibadetPuan2.text = "Puan : "+ rate.toString()
        }
        if(card == "Bet Israel Sinagogu"){
            ibadetPuan3.text = "Puan : "+ rate.toString()
        }
        if(card == "Hz.Yuşa Türbesi"){
            ibadetPuan4.text = "Puan : "+ rate.toString()
        }
        if(card == "Süleymaniye Camii"){
            ibadetPuan5.text = "Puan : "+ rate.toString()
        }
        if(card == "St. Antuan Kilisesi"){
            ibadetPuan6.text = "Puan : "+ rate.toString()
        }
    }
}