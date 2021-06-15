package com.example.workinitalizerapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DictionaryEntity::class],
    version = 1,
    exportSchema = false,
)

abstract class DictionaryDatabase : RoomDatabase() {
    abstract val dictionaryDao:DictionaryDao
}