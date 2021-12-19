package com.example.cachingexample.feature.data.repository

import com.example.cachingexample.feature.domain.model.WordInfo
import com.example.cachingexample.util.Result
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Result<List<WordInfo>>>
}