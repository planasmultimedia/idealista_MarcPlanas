package com.example.idealista_marcplanas.presentation.uiModels

data class AdDetailUiModel(
    val id: Int,
    val homeType: String,
    val price: String,
    val description: String,
    val images : List<String>,
    val rooms : String,
    val bathrooms: String,
    val exterior: String,
    val energyCertificationType: String
)