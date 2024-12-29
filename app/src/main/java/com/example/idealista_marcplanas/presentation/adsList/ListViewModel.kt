package com.example.idealista_marcplanas.presentation.adsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.idealista_marcplanas.data.model.Ad
import com.example.idealista_marcplanas.data.repository.AdRepository
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
    private val repository: AdRepository
) : ViewModel() {

    private val _adsState = MutableStateFlow<Response<List<AdUiModel>>>(Response.Loading)
    val adsState: StateFlow<Response<List<AdUiModel>>> get() = _adsState

    init {
        fetchAds()
    }

    fun fetchAds() {
        _adsState.value = Response.Loading
        viewModelScope.launch {
            val response = repository.getAds()
            _adsState.value = when (response) {
                is Response.Success -> {
                    Response.Success(response.data.map { AdUiMapper.mapToUiModel(it) })
                }
                is Response.Error -> {
                    Response.Error(response.message, response.cause)
                }
                else -> Response.Loading
            }
        }
    }

    fun retry() {
        fetchAds()
    }
}