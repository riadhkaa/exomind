package com.example.exomindtest.Weather.ui.activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exomindtest.R
import com.example.exomindtest.Weather.data.model.WeatherData
import com.example.exomindtest.Weather.ui.adapter.WeatherAdapter
import com.example.exomindtest.Weather.ui.viewmodel.WeatherViewModel
import com.example.exomindtest.Weather.utils.serviceState.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*


class WeatherActivity : AppCompatActivity() {

    var weatherRecycleView: RecyclerView? = null
    var currentProgress:Int = 0
    var progressbar:ProgressBar? = null
    var percentageProgress:Int = 0
    var text_progress:TextView? = null
    var weatherList: ArrayList<WeatherData>? = ArrayList()
    val scope = MainScope()
    var job: Job? = null
    var btn_replay:Button? = null
    var displayElement:Boolean? = false
    val waiting1 = "Nous téléchargeons les données..."
    val waiting2 = "c'est presque fini..."
    val waiting3 = " Plus que quelques secondes avant d’avoir le résultat..."
    val duration = Toast.LENGTH_LONG


    private val viewModel: WeatherViewModel
        get() = getViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideActionBar()
        setContentView(R.layout.activity_weather)
        initComponents()
        startUpdates()
        fetchData()
    }

    private fun initComponents(){
        progressbar = findViewById(R.id.progressbar)
        weatherRecycleView = findViewById(R.id.recyclerView_weather)
        text_progress = findViewById(R.id.text_progress)
        btn_replay = findViewById(R.id.btn_replay)
        progressbar?.setProgressTintList(ColorStateList.valueOf(Color.RED));
        btn_replay?.visibility =View.GONE
    }

    private fun initData(city:String){
        viewModel.fetchWeather(city)
    }

    private fun fetchData(){
        lifecycleScope.launch {
            viewModel._listWeather.collectLatest {
                    resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        weatherList?.add(resource.data!!)
                    }
                    Resource.Status.LOADING -> {
                        println("Loading...")
                    }
                    Resource.Status.ERROR -> {
                        println("Error...")
                    }
                    else->{
                    }
                }
            }
        }
    }
    private fun initRecycler(list : ArrayList<WeatherData>) {
        weatherRecycleView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = WeatherAdapter(viewModel,list ?: ArrayList(),context)
        }
    }

    fun startUpdates() {
        currentProgress = 0
        stopUpdates()
        displayAlert()
        job = scope.launch {
            while(true) {
                cityCall()
                displayButton()
                text_progress?.setText(percentageProgress.toString())
                progressbar?.setProgress(currentProgress)
                currentProgress = currentProgress+10
                delay(10000)
            }
        }
    }
    fun stopUpdates() {
        job?.cancel()
        job = null
    }

    private fun cityCall(){
        if (currentProgress == 0 ){
            initData("Rennes")
        }else if(currentProgress==10){
            initData("Paris")
            percentageProgress = 33
        }else if (currentProgress==20){
            initData("Nantes")
            percentageProgress = 49
        }else if(currentProgress==30){
            initData("Bordeaux")
            percentageProgress = 65
        }else if(currentProgress == 40 ){
            initData("Lyon")
            percentageProgress = 71
        }else if(currentProgress == 60){
            initRecycler(weatherList!!)
            percentageProgress = 100
            displayElement = true
        }
    }

    private fun displayAlert(){
        val toast1 = Toast.makeText(applicationContext, waiting1, duration)
        val toast2 = Toast.makeText(applicationContext, waiting2, duration)
        val toast3 = Toast.makeText(applicationContext, waiting3, duration)

        job = scope.launch {
            while(currentProgress < 60) {
                toast1.show()
                delay(6000)
            }
        }
        job = scope.launch {
            while(currentProgress < 60) {
                toast2.show()
                delay(6000)
            }
        }
        job = scope.launch {
            while(currentProgress < 60) {
                toast3.show()
                delay(6000)
            }
        }
    }

    private fun displayButton(){
        if (displayElement == true){
            progressbar?.visibility = View.GONE
            btn_replay?.visibility =  View.VISIBLE
        }else if(displayElement == false){
            progressbar?.visibility = View.VISIBLE
            btn_replay?.visibility =  View.GONE
        }
        if (btn_replay != null){
            btn_replay?.setOnClickListener {
                percentageProgress = 0
                weatherList?.clear()
                initRecycler(weatherList!!)
                displayElement = false
                startUpdates()
            }
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
    
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@WeatherActivity, HomeActivity::class.java))
        finish()
    }
}