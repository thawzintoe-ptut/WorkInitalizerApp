package com.example.workinitalizerapp

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import com.example.workinitalizerapp.di.cacheModule
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import java.util.concurrent.Executors


open class MainApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
        AndroidThreeTen.init(this)

        WorkManager.initialize(
            applicationContext,
            Configuration.Builder()
                .setExecutor(Executors.newFixedThreadPool(8))
                .build()
        )
    }

    protected open fun startKoin() {
        org.koin.core.context.startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                   cacheModule
                )
            )
        }
    }
}