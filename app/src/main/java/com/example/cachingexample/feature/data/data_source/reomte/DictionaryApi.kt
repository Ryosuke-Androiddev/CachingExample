package com.example.cachingexample.feature.data.data_source.reomte

import com.example.cachingexample.feature.data.data_source.reomte.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET("/api/v2/entries/en/{word}")
    suspend fun getWord(
        @Path("word") word: String
    ): List<WordInfoDto>
}