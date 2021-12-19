package com.example.cachingexample.feature.data.data_source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cachingexample.feature.domain.model.Meaning
import com.example.cachingexample.feature.domain.model.WordInfo
import com.example.cachingexample.util.Constants

@Entity(tableName = Constants.TABLE_NAME)
data class WordInfoEntity(
    val meanings: List<Meaning>,
    val word: String,
    val origin: String,
    val phonetic: String,
    @PrimaryKey
    val id: Int? = null
){

    fun toWordInfo(): WordInfo{

        return WordInfo(
            meanings = meanings,
            word = word,
            origin = origin,
            phonetic = phonetic
        )
    }
}
