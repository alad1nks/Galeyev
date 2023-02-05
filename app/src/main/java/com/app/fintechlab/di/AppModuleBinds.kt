package com.app.fintechlab.di

import com.app.fintechlab.data.repositories.FilmsRepositoryImpl
import com.app.fintechlab.domain.repositories.FilmsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModuleBinds {

    @Singleton
    @Binds
    abstract fun bindScheduleRepository(repo: FilmsRepositoryImpl) : FilmsRepository

}