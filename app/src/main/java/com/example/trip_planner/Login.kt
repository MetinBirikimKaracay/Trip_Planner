package com.example.trip_planner

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trip_planner.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var preferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar!!.title = "Giriş Yap"

        preferences = getSharedPreferences("bilgiler", MODE_PRIVATE)

        binding.btnGirisYap.setOnClickListener{
            var kayitliad = preferences.getString("ad","")
            var kayitlisoyad = preferences.getString("soyad","")
            var kayitliparola = preferences.getString("parola","")

            var girisKullaniciadi = binding.girisKullaniciAdi.text.toString()
            var girisKullanicisoyadi = binding.girisSoyadi.text.toString()
            var girisParola = binding.girisParola.text.toString()

            if((kayitliad == girisKullaniciadi) && (kayitlisoyad == girisKullanicisoyadi) && (kayitliparola==girisParola))
            {

                intent = Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
            }
            else
            {
                Toast.makeText(applicationContext,"Lütfen bilgilerinizi kontrol ediniz",Toast.LENGTH_LONG).show()
            }

        }

        binding.btnKayitOl.setOnClickListener{
            intent = Intent(applicationContext,SignIn::class.java)
            startActivity(intent)
        }
    }
}