package com.app.fintechlab.data.repositories

import android.util.Log
import com.app.fintechlab.data.api.FilmsApi
import com.app.fintechlab.data.db.FilmsDatabase
import com.app.fintechlab.data.toDatabase
import com.app.fintechlab.data.toDomain
import com.app.fintechlab.domain.entities.FilmCardDomain
import com.app.fintechlab.domain.entities.FilmPosterDomain
import com.app.fintechlab.domain.repositories.FilmsRepository
import com.app.fintechlab.domain.toFilmCard
import com.app.fintechlab.domain.toFilmPoster
import com.app.fintechlab.presentation.entities.FilmCard
import com.app.fintechlab.presentation.entities.FilmPoster
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilmsRepositoryImpl @Inject constructor(
    private val api: FilmsApi,
    private val db: FilmsDatabase
) : FilmsRepository {

    private var filmPoster: FilmPosterDomain? = null
    private var topFilms: List<FilmCardDomain>? = null
    private var favouriteFilms: List<FilmCardDomain>? = null

    override suspend fun refreshTopFilms() {
        topFilms = api.getTopFilms().toDomain()
    }

    override suspend fun refreshFavouriteFilms() {
        favouriteFilms = db.filmsDao().getFavouriteFilms().map { it.toDomain() }
    }

    override suspend fun refreshFilmPoster(id: String) {
        filmPoster = api.getFilmPoster(id).toDomain()
        Log.d("filmPoster", "$filmPoster")
    }

    override suspend fun getTopFilmCards(): List<FilmCard> {
        topFilms!!.forEach {topFilm ->
            if (favouriteFilms!!.any { it.id == topFilm.id}) {
                topFilm.favourite = true
            }
        }
        Log.d("answer", "$topFilms")
        return topFilms!!.map{ it.toFilmCard() }
    }

    override suspend fun getFavouriteFilmCards(): List<FilmCard> {
        return favouriteFilms!!.map { it.toFilmCard() }
    }

    override suspend fun getFilmPoster(): FilmPoster {
        Log.d("filmPoster", "${filmPoster!!.toFilmPoster()}")
        return filmPoster!!.toFilmPoster()
    }

    override suspend fun addFavouriteFilm(id: Int) {
        topFilms!!.forEach {
            if (it.id == id) {
                db.filmsDao().insertFavouriteFilm(
                    it.toDatabase()
                )
            }
        }
    }

    override suspend fun removeFavouriteFilm(id: Int) {
        TODO("Not yet implemented")
    }
}