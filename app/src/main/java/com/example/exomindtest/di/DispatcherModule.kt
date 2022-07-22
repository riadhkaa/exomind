package com.example.exomindtest.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val ioDispatcherModule = module {

    single { Dispatchers.IO }
}

