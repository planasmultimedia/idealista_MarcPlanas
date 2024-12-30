package com.example.idealista_marcplanas.di

import android.content.Context
import androidx.room.Room
import com.example.idealista_marcplanas.data.local.FavoritesAdDao
import com.example.idealista_marcplanas.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideAdDao(database: AppDatabase): FavoritesAdDao {
        return database.favoriteAdDao()
    }
}