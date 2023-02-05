package com.app.fintechlab.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite")
data class FavouriteFilmEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val year: String,
    val genre: String,
    val posterUrlPreview: String
)