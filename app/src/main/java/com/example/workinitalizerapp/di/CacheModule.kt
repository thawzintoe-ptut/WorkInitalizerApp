package com.example.workinitalizerapp.di

import android.app.Application
import androidx.room.Room
import com.example.workinitalizerapp.db.DictionaryDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val cacheModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideDictionaryNoDao(get()) }

}

fun provideDatabase(application: Application): DictionaryDatabase {
    return Room.databaseBuilder(application, DictionaryDatabase::class.java, "dictionaryDatabase")
        .allowMainThreadQueries()
        .build()
}

fun provideDictionaryNoDao(database: DictionaryDatabase) = database.dictionaryDao