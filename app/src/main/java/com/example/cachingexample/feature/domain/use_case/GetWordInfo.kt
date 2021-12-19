package com.example.cachingexample.feature.domain.use_case

import com.example.cachingexample.feature.data.repository.WordInfoRepository
import com.example.cachingexample.feature.domain.model.WordInfo
import com.example.cachingexample.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(
    private val repository: WordInfoRepository
) {

    operator fun invoke(word: String): Flow<Result<List<WordInfo>>> {

        if (word.isBlank()){
            return flow {  }
        }
        return repository.getWordInfo(word)
    }
}