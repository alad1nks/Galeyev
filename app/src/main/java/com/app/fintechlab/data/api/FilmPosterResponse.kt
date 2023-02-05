package com.app.fintechlab.data.api

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class FilmPosterResponse(
    @Json(name = "nameRu")
    val nameRu: String?,

    @Json(name = "description")
    val description: String?,

    @Json(name = "genres")
    val genres: List<GenreResponse>?,

    @Json(name = "countries")
    val countries: List<CountryResponse>?,

    @Json(name = "posterUrl")
    val posterUrl: String?
) : Parcelable
