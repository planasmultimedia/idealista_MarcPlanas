package com.example.idealista_marcplanas.domain

import com.example.idealista_marcplanas.data.repository.AdRepository
import com.example.idealista_marcplanas.presentation.mappers.AdUiMapper
import com.example.idealista_marcplanas.presentation.uiModels.AdUiModel
import com.example.idealista_marcplanas.utils.Response
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import javax.inject.Inject

class GetAdsWithFavoritesUseCase @Inject constructor(
    private val repository: AdRepository
) {
    suspend operator fun invoke(): Response<List<AdUiModel>> {
        return try {
            val adsResult = repository.getAds()
            val favorites = repository.getFavorites().first()

            if (adsResult is Response.Success) {
                val favoriteMap = favorites.associateBy { it.id }
                val adsWithFavorites = adsResult.data.map { ad ->
                    val favorite = favoriteMap[ad.propertyCode]
                    AdUiMapper.mapToUiModel(ad, favorite?.favoritedAt)
                }
                Response.Success(adsWithFavorites)
            } else if (adsResult is Response.Error) {
                Response.Error(adsResult.message ?: "Unknown error")
            } else {
                Response.Error("Unexpected error")
            }
        } catch (e: Exception) {
            Response.Error(e.localizedMessage ?: "Error fetching ads")
        }
    }
}