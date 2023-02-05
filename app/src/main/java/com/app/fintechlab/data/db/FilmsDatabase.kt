package com.app.fintechlab.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.fintechlab.data.entities.FavouriteFilmEntity
import com.app.fintechlab.data.entities.TopFilmEntity

@Database(
    entities = [
        FavouriteFilmEntity::class,
        TopFilmEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class FilmsDatabase : RoomDatabase() {
    abstract fun filmsDao(): FilmsDao
}