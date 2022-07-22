package com.example.exomindtest.Weather.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Weather(var id:String,var main:String,var description:String,var icon:String)

data class MainWeather(var temp:String, var feels_like:String,var temp_min:String,
                       var temp_max:String,var pressure:String,var humidity:String)

data class WeatherData(@SerializedName("weather") var weather: List<Weather>)

data class RegionName(@SerializedName("name") var region:String? = null):Serializable
