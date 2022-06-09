package com.example.trip_planner

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.trip_planner.databinding.ActivityMainBinding
import com.example.trip_planner.databinding.ActivitySignInBinding

class SignIn : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        //Başlık Eklemeyi Sağlar
        //Başlık ekle
        actionBar!!.title = "Kayıt Ol"

        binding.btnKaydet.setOnClickListener {
            var kullaniciad = binding.kayitKullaniciAdi.text.toString()
            var kullanicisoyad = binding.kayitSoyadi.text.toString()
            var kullaniciparola = binding.kayitParola.text.toString()
            var SharedPreferences = this.getSharedPreferences("bilgiler", MODE_PRIVATE)
            var editor = SharedPreferences.edit()

            //veri ekleme
            editor.putString("ad", "$kullaniciad").apply()
            editor.putString("soyad", "$kullanicisoyad").apply()
            editor.putString("parola", "$kullaniciparola").apply()

            Toast.makeText(applicationContext, "Kayıt Başarılı!", Toast.LENGTH_LONG).show()

            binding.kayitKullaniciAdi.text.clear()
            binding.kayitSoyadi.text.clear()
            binding.kayitParola.text.clear()
        }

        binding.btnGiriseDon.setOnClickListener {
            intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
        }
    }
}