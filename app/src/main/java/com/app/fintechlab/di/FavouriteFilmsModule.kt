package com.app.fintechlab.di

import androidx.lifecycle.ViewModel
import com.app.fintechlab.presentation.FilmsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FavouriteFilmsModule {
    @Binds
    @IntoMap
    @ViewModelKey(FilmsViewModel::class)
    abstract fun bindViewModel(viewModel: FilmsViewModel): ViewModel
}