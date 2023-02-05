package com.app.fintechlab.di

import android.content.Context
import androidx.room.Room
import com.app.fintechlab.data.api.FilmsApi
import com.app.fintechlab.data.db.FilmsDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
object AppModule {

    private lateinit var INSTANCE: FilmsDatabase

    @Provides
    @Singleton
    fun provideFilmsDatabase(context: Context): FilmsDatabase {
        synchronized(FilmsDatabase::class.java) {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    FilmsDatabase::class.java,
                    "films_database")
                    .build()
            }
        }
        return INSTANCE
    }

    @Provides
    @Singleton
    fun provideFilmsApi(client: OkHttpClient): FilmsApi = Retrofit.Builder()
        .client(client)
        .baseUrl("https://kinopoiskapiunofficial.tech/api/v2.2/films/")
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()))
        .build()
        .create(FilmsApi::class.java)

    @Provides
    @Singleton
    fun provideHttpClient() = OkHttpClient.Builder().addInterceptor {
        val requestBuilder = it.request().newBuilder()
        requestBuilder.addHeader("X-API-KEY", "eb422d4b-4994-4fc9-94cc-c2395287c700")
        it.proceed(requestBuilder.build())
    }.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()
}