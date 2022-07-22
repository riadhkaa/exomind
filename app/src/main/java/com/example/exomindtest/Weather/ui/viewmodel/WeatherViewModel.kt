package com.example.exomindtest.Weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exomindtest.Weather.data.model.Weather
import com.example.exomindtest.Weather.data.model.WeatherData
import com.example.exomindtest.Weather.data.repository.IWeatherRepository
import com.example.exomindtest.Weather.data.repository.WeatherRepository
import com.example.exomindtest.Weather.utils.serviceState.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WeatherViewModel(var weatherRepository: IWeatherRepository):ViewModel() {

    val _listWeather = MutableStateFlow<Resource<WeatherData>>(Resource.loading())
    val listWeather: StateFlow<Resource<WeatherData>>
        get() = _listWeather


    fun fetchWeather(city: String) {
        viewModelScope.launch {
            weatherRepository.fetchWeatherByCity(city).collect {
            _listWeather.value = it
            }
        }
    }
}