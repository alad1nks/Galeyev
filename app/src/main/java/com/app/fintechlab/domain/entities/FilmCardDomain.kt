package com.app.fintechlab.domain.entities

data class FilmCardDomain(
    val id: Int,
    val name: String,
    val year: String,
    val genre: String,
    val posterUrlPreview: String,
    var favourite: Boolean
)
