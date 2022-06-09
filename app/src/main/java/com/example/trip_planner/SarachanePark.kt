package com.example.trip_planner

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_sarachane_park.*
import kotlinx.android.synthetic.main.activity_sarachane_park.comment
import kotlinx.android.synthetic.main.activity_sarachane_park.mainLayout
import kotlinx.android.synthetic.main.activity_sarachane_park.spParkPuan
import kotlinx.android.synthetic.main.activity_sarachane_park.yorumEkle
import kotlinx.android.synthetic.main.activity_topkapi_park.*

class SarachanePark : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sarachane_park)
        var totalPoint:Int = 0
        var counter:Int = 0

        val context = this
        var db  = DataBaseHelper(this)
        var data = db.readData()

        val actionBar = supportActionBar
        //Başlık Eklemeyi Sağlar
        actionBar!!.title = "Saraçhane Parkı"
        //Sol üstte geri git ikonu oluşturur
        actionBar.setDisplayHomeAsUpEnabled(true)


        // Verilen Puanı Tuttuğumuz Değişken
        var SelectedPoint:String = ""
        var adi:String = "Metin"
        var soyadi:String = "Karaçay"

        //Veritabanına Daha Önce Kaydedilmiş Verileri Yazdırmak İçin Gereken Kod
        for(i in 0 until data.size){
            if(data.size == 0){
                val tv_dynamic = TextView(this)
                tv_dynamic.textSize = 20f
                tv_dynamic.text = getString(R.string.yorumsuz)
                mainLayout.addView(tv_dynamic)
                break
            }else if(data.get(i).placeName == actionBar.title && data.size > 0) {
                totalPoint = totalPoint + data.get(i).point
                counter++
                val tv_dynamic = TextView(this)
                var yorum1 = "<b>" + data.get(i).name + " " + data.get(i).surName + "</b>" + ": " +  data.get(i).comment
                tv_dynamic.setText(Html.fromHtml(yorum1))
                mainLayout.addView(tv_dynamic)
            }
        }

        if(totalPoint == 0){
            ratingBarSarachane.rating = 3F
        }else{
            var rate = totalPoint / counter
            ratingBarSarachane.rating = rate.toFloat()
        }

        var puan = resources.getStringArray(R.array.Puanlar)
        if (spParkPuan != null){
            val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,puan)
            spParkPuan.adapter = adapter
            //Verilen Puanı Veritabanına Atmak İçin Liste İçindeki Yerini Buluyoruz
            spParkPuan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                                            position: Int, id: Long) {
                    SelectedPoint = puan[position]

                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }

        yorumEkle.setOnClickListener {
            val tv_dynamic = TextView(this)
            var yorum = "<b>" + adi + " " + soyadi + "</b>" + ": " +  comment.text.toString()
            tv_dynamic.textSize = 15f
            //tv_dynamic.text = comment.text.toString()

            tv_dynamic.setText(Html.fromHtml(yorum))

            // Activity xml Dosyasına Yeni Bir Textview Eklemeyi sağlar
            mainLayout.addView(tv_dynamic)

            //Veritanına Kaydetme İşlemi
            var name = adi
            var surName = soyadi
            var comment = comment.text.toString()
            var point = SelectedPoint.toInt()
            var placeName = actionBar.title.toString()

            var comments = Comments(name,surName,placeName,comment,point)

            db.insertData(comments)

            hideKeyboard()
        }
    }
    // Klavyeyi kapatma Fonksiyonu
    fun hideKeyboard() {
        val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var currentFocus = this.currentFocus
        if(currentFocus == null){
            currentFocus = View(this)
        }
        imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }
}