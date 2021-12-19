package com.example.cachingexample.feature.domain.repository

import com.example.cachingexample.feature.data.data_source.local.WordInfoDao
import com.example.cachingexample.feature.data.data_source.reomte.DictionaryApi
import com.example.cachingexample.feature.data.repository.WordInfoRepository
import com.example.cachingexample.feature.domain.model.WordInfo
import com.example.cachingexample.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
): WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Result<List<WordInfo>>> = flow {
        emit(Result.Loading())

        val wordCacheInfo = dao.searchWord(word).map { it.toWordInfo() }
        emit(Result.Loading(data = wordCacheInfo))

        try {

            val wordRemoteInfo = api.getWord(word)
            dao.deleteWordsInfo(wordRemoteInfo.map { it.word })
            dao.insertWordInfo(wordRemoteInfo.map { it.toWordInfoEntity() })

        } catch (e: HttpException){
            emit(Result.Error(
                wordCacheInfo,
                "error occurred"
            ))
        } catch (e: IOException){
            emit(Result.Error(
                wordCacheInfo,
                "error occurred"
            ))
        }

        val newWordInfo = dao.searchWord(word).map { it.toWordInfo() }
        emit(Result.Success(newWordInfo))
    }
}