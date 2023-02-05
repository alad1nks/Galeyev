package com.app.fintechlab.data.api

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class CountryResponse(
    @Json(name = "country")
    val country: String?
) : Parcelable
