package com.example.idealista_marcplanas.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.idealista_marcplanas.data.model.FavoriteAdEntity

@Database(entities = [FavoriteAdEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteAdDao(): FavoritesAdDao
}