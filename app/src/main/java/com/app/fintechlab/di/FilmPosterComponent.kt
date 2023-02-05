package com.app.fintechlab.di

import com.app.fintechlab.presentation.poster.FilmPosterFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [
        FilmPosterModule::class
    ]
)
interface FilmPosterComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): FilmPosterComponent
    }
    fun inject(fragment: FilmPosterFragment)
}