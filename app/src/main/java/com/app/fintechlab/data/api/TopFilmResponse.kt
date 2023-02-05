package com.app.fintechlab.data.api

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class TopFilmResponse(

    @Json(name = "filmId")
    val filmId: Int?,

    @Json(name = "nameRu")
    val nameRu: String?,

    @Json(name = "year")
    val year: String?,

    @Json(name = "countries")
    val countries: List<CountryResponse>?,

    @Json(name = "genres")
    val genres: List<GenreResponse>?,

    @Json(name = "rating")
    val rating: String?,

    @Json(name = "ratingVoteCount")
    val ratingVoteCount: Int?,

    @Json(name = "posterUrl")
    val posterUrl: String?,

    @Json(name = "posterUrlPreview")
    val posterUrlPreview: String?,

) : Parcelable
