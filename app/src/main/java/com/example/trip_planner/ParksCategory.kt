package com.example.trip_planner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_parks_category.*

class ParksCategory : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parks_category)

        var places = ArrayList<String>()

        var totalPoint:Int = 0
        var counter:Int = 0

        var db  = DataBaseHelper(this)
        var data = db.readData()

        val actionBar = supportActionBar
        //Başlık Eklemeyi Sağlar
        actionBar!!.title = "Parklar"
        //Sol üstte geri git ikonu oluşturur
        actionBar.setDisplayHomeAsUpEnabled(true)


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

        cardPark1.setOnClickListener {
            val intent = Intent(this,EmirganKorusu::class.java)
            startActivity(intent)
        }

        cardPark2.setOnClickListener {
            val intent = Intent(this,PolonezkoyPark::class.java)
            startActivity(intent)
        }
        cardPark3.setOnClickListener {
            val intent = Intent(this,GoztepePark::class.java)
            startActivity(intent)
        }

        cardPark4.setOnClickListener {
            val intent = Intent(this,SarachanePark::class.java)
            startActivity(intent)
        }

        cardPark5.setOnClickListener {
            val intent = Intent(this,TopkapiPark::class.java)
            startActivity(intent)
        }

        cardPark6.setOnClickListener {
            val intent = Intent(this,YildizPark::class.java)
            startActivity(intent)
        }
    }

    fun writePoint(totalPoint:Int, counter:Int,card:String){

        var rate:Int = totalPoint / counter
        rate.toFloat()

        if(card == "Emirgan Korusu"){
            parkPuan1.text = "Puan : "+ rate.toString()
        }
        if(card == "Polonezköy Tabiat Parkı"){
            parkPuan2.text = "Puan : "+ rate.toString()
        }
        if(card == "Göztepe 60.Yıl Parkı"){
            parkPuan3.text = "Puan : "+ rate.toString()
        }
        if(card == "Saraçhane Parkı"){
            parkPuan4.text = "Puan : "+ rate.toString()
        }
        if(card == "Topkapı Parkı"){
            parkPuan5.text = "Puan : "+ rate.toString()
        }
        if(card == "Yıldız Parkı"){
            parkPuan6.text = "Puan : "+ rate.toString()
        }
    }
}