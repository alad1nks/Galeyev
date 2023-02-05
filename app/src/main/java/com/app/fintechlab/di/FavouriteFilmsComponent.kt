package com.app.fintechlab.di

import com.app.fintechlab.presentation.favouritefilms.FilmsFavouriteFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [
        FavouriteFilmsModule::class
    ]
)
interface FavouriteFilmsComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): FavouriteFilmsComponent
    }
    fun inject(fragment: FilmsFavouriteFragment)
}