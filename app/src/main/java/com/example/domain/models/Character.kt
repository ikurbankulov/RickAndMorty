package com.example.domain.models

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val image: String,
    val created: String,
    val gender: String,
    val location: Location,
    val species: String,
    val type: String,
    val url: String,
    var isBookMarked: Boolean
)
// TODO: удалить не нужные поля 