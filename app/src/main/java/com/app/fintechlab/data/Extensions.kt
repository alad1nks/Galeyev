package com.app.fintechlab.data

import com.app.fintechlab.data.api.FilmPosterResponse
import com.app.fintechlab.data.api.TopFilmsResponse
import com.app.fintechlab.data.entities.FavouriteFilmEntity
import com.app.fintechlab.domain.entities.FilmCardDomain
import com.app.fintechlab.domain.entities.FilmPosterDomain

fun TopFilmsResponse.toDomain(): List<FilmCardDomain> {
    return films!!.map { film ->
        FilmCardDomain(
            id = film.filmId!!,
            name = film.nameRu!!,
            year = film.year!!,
            genre = film.genres!![0].genre!!,
            posterUrlPreview = film.posterUrlPreview!!,
            favourite = false
        )
    }
}

fun FavouriteFilmEntity.toDomain(): FilmCardDomain {
    return FilmCardDomain(
        id = id,
        name = name,
        year = year,
        genre = genre,
        posterUrlPreview = posterUrlPreview,
        favourite = true
    )
}

fun FilmPosterResponse.toDomain(): FilmPosterDomain {
    return FilmPosterDomain(
        name = nameRu!!,
        description = description!!,
        genres = genres!!.map { it.genre!! },
        countries = countries!!.map { it.country!! },
        posterUrl = posterUrl!!
    )
}

fun FilmCardDomain.toDatabase(): FavouriteFilmEntity {
    return FavouriteFilmEntity(
        id = id,
        name = name,
        year = year,
        genre = genre,
        posterUrlPreview = posterUrlPreview
    )
}