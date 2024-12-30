package com.example.idealista_marcplanas.data.repository

import androidx.room.Dao
import com.example.idealista_marcplanas.data.api.AdApi
import com.example.idealista_marcplanas.data.local.FavoritesAdDao
import com.example.idealista_marcplanas.data.model.Ad
import com.example.idealista_marcplanas.data.model.AdDetail
import com.example.idealista_marcplanas.data.model.FavoriteAdEntity
import com.example.idealista_marcplanas.utils.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AdRepository @Inject constructor (
    private val adApi: AdApi,
    private val favoriteAdDao: FavoritesAdDao
){
    suspend fun getAds(): Response<List<Ad>> {
        return try {
            val response = adApi.getAds()
            Response.Success(response)
        } catch (e: Exception) {
            Response.Error("Failed to fetch ads: ${e.localizedMessage}", e)
        }
    }

    suspend fun getAdDetail(): Response<AdDetail> {
        return try {
            val response = adApi.getAdDetail()
            Response.Success(response)
        } catch (e: Exception) {
            Response.Error("Failed to fetch ad detail: ${e.localizedMessage}", e)
        }
    }

    fun getFavorites(): Flow<List<FavoriteAdEntity>> = favoriteAdDao.getAllFavorites()

    suspend fun addToFavorites(adId: String) {
        favoriteAdDao.addToFavorites(
            FavoriteAdEntity(
                id = adId,
                favoritedAt = System.currentTimeMillis()
            )
        )
    }

    suspend fun removeFromFavorites(id: String) {
        favoriteAdDao.removeFromFavorites(id)
    }
}