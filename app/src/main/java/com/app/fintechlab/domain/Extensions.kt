package com.app.fintechlab.domain

import com.app.fintechlab.R
import com.app.fintechlab.domain.entities.FilmCardDomain
import com.app.fintechlab.domain.entities.FilmPosterDomain
import com.app.fintechlab.presentation.entities.FilmCard
import com.app.fintechlab.presentation.entities.FilmPoster
import java.util.Locale

fun FilmCardDomain.toFilmCard(): FilmCard {
    return FilmCard(
        id = id,
        name = name,
        genreYear = "${genre.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }} ($year)",
        posterUrlPreview = posterUrlPreview,
        favourite = when(favourite) {
            true -> R.drawable.favourite_star
            else -> R.drawable.empty_shape
        }
    )
}

fun FilmPosterDomain.toFilmPoster(): FilmPoster {
    return FilmPoster(
        name = name,
        description = description,
        genres = genres.toString(),
        countries = countries.toString(),
        posterUrl = posterUrl
    )
}

