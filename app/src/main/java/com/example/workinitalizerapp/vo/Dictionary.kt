package com.example.workinitalizerapp.vo

import com.example.workinitalizerapp.db.DictionaryEntity

data class Dictionary(
    val myanmar:String,
    val english:String,
    val partOfSpeech:String
)

object DictionaryMapper{
    fun mapResponse(dictionary: Dictionary):DictionaryEntity{
        return DictionaryEntity(
            id = System.currentTimeMillis(),
            myanmar = dictionary.myanmar,
            english = dictionary.english,
            partOfSpeech = dictionary.partOfSpeech
        )
    }
}