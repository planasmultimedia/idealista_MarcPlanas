package com.example.idealista_marcplanas.domain

import com.example.idealista_marcplanas.data.model.Ad
import com.example.idealista_marcplanas.data.repository.AdRepository
import com.example.idealista_marcplanas.presentation.uiModels.AdUiModel
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: AdRepository
) {
    suspend operator fun invoke(ad: AdUiModel) {
        if (ad.isFavorite) {
            repository.removeFromFavorites(ad.id)
        } else {
            repository.addToFavorites(ad.id)
        }
    }
}