package com.example.workinitalizerapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary")
data class DictionaryEntity (

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id:Long,

    @ColumnInfo(name = "myanmar")
    val myanmar:String,

    @ColumnInfo(name = "english")
    val english:String,

    @ColumnInfo(name = "partOfSpeech")
    val partOfSpeech:String,
)