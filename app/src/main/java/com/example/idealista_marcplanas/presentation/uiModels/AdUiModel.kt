package com.example.idealista_marcplanas.presentation.uiModels

data class AdUiModel(
    val id: String,
    val title : String,
    val address: String,
    val price: String,
    val parkingInfo : String,
    val images: List<String>,
    val roomInfo: String,
    val sizeInfo: String,
    val operation : String
)