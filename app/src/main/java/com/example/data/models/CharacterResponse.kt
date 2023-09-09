package com.example.data.models

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("results")
    val result: List<CharacterEntity>
)
