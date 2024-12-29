package com.example.idealista_marcplanas.data.model

data class AdDetail(
    val adId : Int,
    val priceInfo: Price,
    val homeType: String,
    val propertyComment: String,
    val multimedia: Multimedia,
    val moreCharacteristics: MoreCharacteristics
)

data class MoreCharacteristics(
    val roomNumber: Int,
    val bathNumber: Int,
    val exterior: Boolean,
    val energyCertificationType: String,
)