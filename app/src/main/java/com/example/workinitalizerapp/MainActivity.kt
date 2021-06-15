package com.example.workinitalizerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import com.example.workinitalizerapp.worker.PopulateDictionaryWorker
import org.threeten.bp.Duration
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val request =
            PeriodicWorkRequestBuilder<PopulateDictionaryWorker>(Duration.ofHours(1).seconds, TimeUnit.SECONDS)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            PopulateDictionaryWorker.NAME,
            ExistingPeriodicWorkPolicy.REPLACE,
            request
        )

    }
}