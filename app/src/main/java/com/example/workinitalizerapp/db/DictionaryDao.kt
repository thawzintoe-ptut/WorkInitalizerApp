package com.example.workinitalizerapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface DictionaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDictionary(dictionaryList: List<DictionaryEntity>):List<Long>
}