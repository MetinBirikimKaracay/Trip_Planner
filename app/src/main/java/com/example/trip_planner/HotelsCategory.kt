package com.example.trip_planner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_hotels_category.*

class HotelsCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotels_category)
        var places2 = ArrayList<String>()

        var totalPoint:Int = 0
        var counter:Int = 0

        var db  = DataBaseHelper(this)
        var data = db.readData()


        val actionBar = supportActionBar
        //Başlık Eklemeyi Sağlar
        actionBar!!.title = "Oteller"

        for(i in 0 until data.size){
            var temp:String = data.get(i).placeName

            if (places2.isEmpty()){
                places2.add(temp)
            }
            else if(places2.indexOf(temp) == -1){
                places2.add(temp)
            }
        }

        for (i in 0..1){
            for(j in 0 until data.size){
                if(data.get(j).placeName == places2[i] && data.size > 0) {
                    totalPoint = totalPoint + data.get(j).point
                    counter++
                }
            }
            writePoint(totalPoint, counter, places2[i])
            totalPoint = 0
            counter = 0
        }

        cardOtel1.setOnClickListener {
            val intent = Intent(this,HotelInter::class.java)
            startActivity(intent)
        }

        cardOtel2.setOnClickListener {
            val intent = Intent(this,HotelHoliday::class.java)
            startActivity(intent)
        }
        cardOtel3.setOnClickListener {
            val intent = Intent(this,HotelCrowne::class.java)
            startActivity(intent)
        }
        cardOtel4.setOnClickListener {
            val intent = Intent(this,HotelGreen::class.java)
            startActivity(intent)
        }
    }
    fun writePoint(totalPoint:Int, counter:Int,card:String){

      var rate:Int = totalPoint / counter
      rate.toFloat()

      if(card == "Intercontinental Hotel"){
          otelPuan1.text = "Puan : "+ rate.toString()
      }
      if(card == "Holiday Inn Hotel"){
          otelPuan2.text = "Puan : "+ rate.toString()
      }
      if(card == "Crowne Plaza"){
          otelPuan3.text = "Puan : "+ rate.toString()
      }
      if(card == "The Green Park Hotel"){
          otelPuan4.text = "Puan : "+ rate.toString()
      }
  }
}