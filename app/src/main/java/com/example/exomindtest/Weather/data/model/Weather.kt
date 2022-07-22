package com.example.exomindtest.Weather.data.model

import com.google.gson.annotations.SerializedName

data class Weather(var id:String,var main:String,var description:String,var icon:String)
data class WeatherData(@SerializedName("weather") var weather: List<Weather>,
                       @SerializedName("main") var main:Main,
                       @SerializedName("clouds") var cloud:Cloud,
                       @SerializedName("name") var region: String)
data class Main(@SerializedName("temp") var temperature: String)
data class Cloud(@SerializedName("all") var cloud: String)

