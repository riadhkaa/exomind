package com.example.exomindtest.Weather.data.repository

import android.content.Context
import com.example.exomindtest.Weather.data.api.WeatherService
import com.example.exomindtest.Weather.data.model.Weather
import com.example.exomindtest.Weather.data.model.WeatherData
import com.example.exomindtest.Weather.utils.Constants
import com.example.exomindtest.Weather.utils.serviceState.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

interface IWeatherRepository {
    suspend fun fetchWeatherByCity(city: String) : Flow<Resource<WeatherData>>

    
}
class WeatherRepository(private val api : WeatherService,
                        private val context : Context,
                        dispatcher : CoroutineDispatcher):IWeatherRepository{

    override suspend fun fetchWeatherByCity(city: String): Flow<Resource<WeatherData>> = flow{
        emit(Resource.loading())
        try {
            val filter = "weather?q=${city}&appid=fad13a84639a833656caf13a58f5d8af"
            emit(Resource.success(api.getWeatherByCity(Constants.WEATHER_URL+filter)))
        }catch (err: Exception){
            println("err $err")

        }
    }

}