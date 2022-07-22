
@file:Suppress("unused")
package com.example.exomindtest.Weather

import android.app.Application
import androidx.multidex.BuildConfig
import com.example.exomindtest.di.ioDispatcherModule
import com.example.exomindtest.di.networkModule
import com.example.exomindtest.di.repositoryModule
import com.example.exomindtest.di.viewModelModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class ExomindApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ExomindApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule,
                    ioDispatcherModule
                )
            )
        }
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}