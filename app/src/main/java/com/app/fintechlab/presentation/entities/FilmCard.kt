package com.app.fintechlab.presentation.entities

import androidx.annotation.DrawableRes

data class FilmCard(
    val id: Int,
    val name: String,
    val genreYear: String,
    val posterUrlPreview: String,
    @DrawableRes var favourite: Int
)
