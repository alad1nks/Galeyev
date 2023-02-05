package com.app.fintechlab.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.fintechlab.data.entities.FavouriteFilmEntity

@Dao
interface FilmsDao {

    @Query("SELECT * FROM favourite")
    fun getFavouriteFilms(): List<FavouriteFilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavouriteFilm(favouriteFilm: FavouriteFilmEntity)

    @Query("DELETE FROM favourite")
    fun clear()

    @Query("DELETE FROM favourite WHERE id = :id")
    fun removeFavouriteFilm(id: Int)

}