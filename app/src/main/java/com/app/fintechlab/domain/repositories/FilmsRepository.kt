package com.app.fintechlab.domain.repositories

import com.app.fintechlab.domain.entities.FilmCardDomain
import com.app.fintechlab.presentation.entities.FilmCard
import com.app.fintechlab.presentation.entities.FilmPoster

interface FilmsRepository {

    suspend fun refreshTopFilms()
    suspend fun refreshFavouriteFilms()
    suspend fun refreshFilmPoster(id: String)

    suspend fun getTopFilmCards(): List<FilmCard>
    suspend fun getFavouriteFilmCards(): List<FilmCard>
    suspend fun getFilmPoster(): FilmPoster

    suspend fun addFavouriteFilm(id: Int)
    suspend fun removeFavouriteFilm(id: Int)
}