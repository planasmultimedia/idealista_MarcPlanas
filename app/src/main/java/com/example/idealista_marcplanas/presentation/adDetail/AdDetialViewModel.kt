package com.example.idealista_marcplanas.presentation.adDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.idealista_marcplanas.data.repository.AdRepository
import com.example.idealista_marcplanas.presentation.mappers.AdDetailUiMapper
import com.example.idealista_marcplanas.presentation.mappers.AdUiMapper
import com.example.idealista_marcplanas.presentation.uiModels.AdDetailUiModel
import com.example.idealista_marcplanas.presentation.uiModels.AdUiModel
import com.example.idealista_marcplanas.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdDetailViewModel @Inject constructor(
    private val repository: AdRepository
) : ViewModel() {

    private val _detailState = MutableStateFlow<Response<AdDetailUiModel>>(Response.Loading)
    val detailState: StateFlow<Response<AdDetailUiModel>> get() = _detailState

    init {
        fetchAdDetails()
    }

    fun fetchAdDetails() {
        _detailState.value = Response.Loading
        viewModelScope.launch {
            val response = repository.getAdDetail()
            _detailState.value = when (response) {
                is Response.Success -> {
                    Response.Success(AdDetailUiMapper.mapToUiModel(response.data))
                }
                is Response.Error -> {
                    Response.Error(response.message, response.cause)
                }
                else -> Response.Loading
            }
        }
    }

    fun retry() {
        fetchAdDetails()
    }

}