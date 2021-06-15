package com.example.workinitalizerapp.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.workinitalizerapp.R
import com.example.workinitalizerapp.db.DictionaryDao
import com.example.workinitalizerapp.vo.Dictionary
import com.example.workinitalizerapp.vo.DictionaryMapper
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import kotlin.Exception

class PopulateDictionaryWorker(
    private val context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params), KoinComponent {

    private val dictionaryDao: DictionaryDao by inject()

    companion object {
        val NAME = "DICTIONARY_WORKER"
    }


    override suspend fun doWork(): Result {
        return try {

            val dictionaryList = mutableListOf<Dictionary>()
            val inputStream: InputStream = context.resources.openRawResource(R.raw.mmtab_unicode)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val csvParser = CSVParser(bufferedReader, CSVFormat.DEFAULT)
            for (csvRecord in csvParser) {
                val myanmar = csvRecord.get(0)
                val english = csvRecord.get(1)
                val partOfSpeech = csvRecord.get(2)
                val dictionary = Dictionary(myanmar, english, partOfSpeech)
                dictionaryList.add(dictionary)
            }
            dictionaryDao.insertAllDictionary(dictionaryList.map(DictionaryMapper::mapResponse))
            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }

    }
}