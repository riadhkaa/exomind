package com.example.exomindtest.Weather.data.api
import com.example.exomindtest.Weather.data.model.WeatherData
import retrofit2.http.*
interface WeatherService {

    @GET
    suspend fun getWeatherByCity(@Url URL:String):WeatherData
}