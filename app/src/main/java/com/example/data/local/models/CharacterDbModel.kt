package com.example.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_character")
data class CharacterDbModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val image: String,
    val gender: String,
    val location: LocationDbModel,
    val species: String,
    val type: String,
    val url: String,
)