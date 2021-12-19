package com.example.cachingexample.feature.data.data_source.reomte.dto


import com.google.gson.annotations.SerializedName

data class PhoneticDto(
    @SerializedName("audio")
    val audio: String,
    @SerializedName("text")
    val text: String
)