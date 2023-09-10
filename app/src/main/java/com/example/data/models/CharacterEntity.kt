package com.example.data.models

import com.google.gson.annotations.SerializedName

data class CharacterEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("location")
    val location: LocationEntity,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String,
)