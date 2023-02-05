package com.app.fintechlab.data.api

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class TopFilmsResponse(

    @Json(name = "pagesCount")
    val pagesCount: Int?,

    @Json(name = "films")
    val films: List<TopFilmResponse>?

) : Parcelable
