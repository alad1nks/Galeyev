package com.app.fintechlab.di

import android.content.Context
import com.app.fintechlab.domain.repositories.FilmsRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AppModuleBinds::class,
        AppSubcomponentsModule::class,
        ViewModelBuilderModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun favouriteFilmsComponent(): FavouriteFilmsComponent.Factory
    fun topFilmsComponent(): TopFilmsComponent.Factory
    fun filmPosterComponent(): FilmPosterComponent.Factory

    val filmsRepository: FilmsRepository
}