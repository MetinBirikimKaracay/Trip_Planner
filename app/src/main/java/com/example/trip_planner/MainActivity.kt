package com.example.trip_planner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val actionBar = supportActionBar
        //Başlık Eklemeyi Sağlar
        actionBar!!.title = "Seyahat Planlayıcı"

        btnParks.setOnClickListener {
            val intent = Intent(this,ParksCategory::class.java)
            startActivity(intent)
        }
        btnLibraries.setOnClickListener{
            val intent = Intent(this,LibrariesCategory::class.java)
            startActivity(intent)
        }
        btnHistorical.setOnClickListener {
            val intent = Intent(this,HistoricalCategory::class.java)
            startActivity(intent)
        }
        btnHotels.setOnClickListener {
            val intent = Intent(this,HotelsCategory::class.java)
            startActivity(intent)
        }
        btnParking.setOnClickListener {
            val intent = Intent(this,OtoparkCategory::class.java)
            startActivity(intent)
        }
        btnMarkets.setOnClickListener {
            val intent = Intent(this,MarketCategory::class.java)
            startActivity(intent)
        }
        btnWorship.setOnClickListener {
            val intent = Intent(this,IbadethaneCategory::class.java)
            startActivity(intent)
        }
        btnExit.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }
    }
}
