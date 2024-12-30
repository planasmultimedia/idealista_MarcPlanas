package com.example.idealista_marcplanas.presentation.adsList

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.idealista_marcplanas.data.model.Ad
import com.example.idealista_marcplanas.data.repository.AdRepository
import com.example.idealista_marcplanas.domain.GetAdsWithFavoritesUseCase
import com.example.idealista_marcplanas.domain.ToggleFavoriteUseCase
import com.example.idealista_marcplanas.presentation.mappers.AdUiMapper
import com.example.idealista_marcplanas.presentation.uiModels.AdUiModel
import com.example.idealista_marcplanas.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getAdsWithFavoritesUseCase: GetAdsWithFavoritesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _adsState = MutableStateFlow<Response<List<AdUiModel>>>(Response.Loading)
    val adsState: StateFlow<Response<List<AdUiModel>>> get() = _adsState

    init {
        fetchAds()
    }

    fun fetchAds() {
        _adsState.value = Response.Loading
        viewModelScope.launch {
            val response = getAdsWithFavoritesUseCase.invoke()
            _adsState.value = when (response) {
                is Response.Success -> {
                    Response.Success(response.data)
                }
                is Response.Error -> {
                    Response.Error(response.message, response.cause)
                }
                else -> Response.Loading
            }
        }
    }

    fun toggleFavorite(ad: AdUiModel) {
        updateFavoriteState(ad)
        viewModelScope.launch {
            try {
                toggleFavoriteUseCase(ad)
            } catch (e: Exception) {
                revertFavoriteState(ad)
            }
        }
    }

    private fun updateFavoriteState(ad: AdUiModel) {
        _adsState.value = when (val currentState = _adsState.value) {
            is Response.Success -> {
                val updatedAds = currentState.data.map {
                    if (it.id == ad.id) it.copy(
                        isFavorite = !it.isFavorite,
                        favoritedAt = System.currentTimeMillis(),
                    ) else it
                }
                Response.Success(updatedAds)
            }
            else -> currentState
        }
    }

    private fun revertFavoriteState(ad: AdUiModel) {
        _adsState.value = when (val currentState = _adsState.value) {
            is Response.Success -> {
                val revertedAds = currentState.data.map {
                    if (it.id == ad.id) it.copy(
                        isFavorite = ad.isFavorite,
                        favoritedAt = ad.favoritedAt
                    ) else it
                }
                Response.Success(revertedAds)
            }
            else -> currentState
        }
    }


    fun retry() {
        fetchAds()
    }
}