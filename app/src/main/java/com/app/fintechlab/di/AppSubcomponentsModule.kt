package com.app.fintechlab.di

import dagger.Module

@Module(
    subcomponents = [
        FavouriteFilmsComponent::class,
        TopFilmsComponent::class,
        FilmPosterComponent::class
    ]
)
object AppSubcomponentsModule