package com.app.fintechlab.di

import com.app.fintechlab.presentation.topfilms.FilmsTopFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [
        TopFilmsModule::class
    ]
)
interface TopFilmsComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): TopFilmsComponent
    }
    fun inject(fragment: FilmsTopFragment)
}