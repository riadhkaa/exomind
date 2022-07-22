package com.example.exomindtest.di

import com.example.exomindtest.Weather.data.repository.IWeatherRepository
import com.example.exomindtest.Weather.data.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<IWeatherRepository> { WeatherRepository(get(), get(), get()) }




}
