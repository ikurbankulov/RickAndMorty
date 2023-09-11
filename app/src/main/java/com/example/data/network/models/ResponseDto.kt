package com.example.data.network.models

import com.google.gson.annotations.SerializedName

data class ResponseDto(
    @SerializedName("results")
    val result: List<CharacterDto>
)
