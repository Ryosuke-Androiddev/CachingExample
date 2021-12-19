package com.example.cachingexample.feature.presentation.word_list

import com.example.cachingexample.feature.domain.model.WordInfo

data class WordInfoState(
    val wordInfo: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)
