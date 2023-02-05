package com.app.fintechlab.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "top")
data class TopFilmEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val year: String,
    val country: String,
    val genre: String
)
