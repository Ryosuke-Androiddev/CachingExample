package com.example.cachingexample.feature.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cachingexample.feature.data.data_source.local.entity.WordInfoEntity

@Dao
interface WordInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfo(wordInfo: List<WordInfoEntity>)

    @Query("DELETE FROM word_info WHERE word IN(:words)")
    suspend fun deleteWordsInfo(words: List<String>)

    @Query("SELECT * FROM word_info WHERE word LIKE '%' || :word || '%'")
    suspend fun searchWord(word: String): List<WordInfoEntity>

}