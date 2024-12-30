package com.example.idealista_marcplanas.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.idealista_marcplanas.data.model.FavoriteAdEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesAdDao {
    @Query("SELECT * FROM favorite_ads")
    fun getAllFavorites(): Flow<List<FavoriteAdEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(ad: FavoriteAdEntity)

    @Query("DELETE FROM favorite_ads WHERE id = :id")
    suspend fun removeFromFavorites(id: String)
}