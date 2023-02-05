package com.app.fintechlab.domain.entities

data class FilmPosterDomain(
    val name: String,
    val description: String,
    val genres: List<String>,
    val countries: List<String>,
    val posterUrl: String
)
