package com.app.fintechlab.data.api

import retrofit2.http.GET
import retrofit2.http.Url

interface FilmsApi {

    @GET("top")
    suspend fun getTopFilms(): TopFilmsResponse

    @GET
    suspend fun getFilmPoster(@Url url: String): FilmPosterResponse
}