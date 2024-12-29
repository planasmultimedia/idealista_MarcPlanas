package com.example.idealista_marcplanas.di

import com.example.idealista_marcplanas.data.api.AdApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://idealista.github.io/android-challenge/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideAdApi(retrofit: Retrofit): AdApi {
        return retrofit.create(AdApi::class.java)
    }
}