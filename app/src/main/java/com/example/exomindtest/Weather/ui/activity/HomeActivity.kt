package com.example.exomindtest.Weather.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.exomindtest.R

class HomeActivity : AppCompatActivity() {

    private var btnSeeWeather:RelativeLayout? = null
    var btn_text:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        hideActionBar()
        setContentView(R.layout.activity_home)
        initComponents()
        initData()
    }

    private fun initComponents(){
        btnSeeWeather = findViewById(R.id.btn_see_weather)
    }

    private fun initData(){
        seeWeather()
    }

    private fun seeWeather(){
        btnSeeWeather?.setOnClickListener {
            val intent = Intent(this@HomeActivity, WeatherActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun hideActionBar(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
    }
}