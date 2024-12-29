package com.example.idealista_marcplanas.data.model

data class Ad(
    val propertyCode: String,
    val propertyType: String,
    val thumbnail: String,
    val operation : String,
    val multimedia: Multimedia,
    val parkingSpace: ParkingSpace?,

    val neighborhood : String,
    val municipality : String,
    val address: String,
    val rooms: Int,
    val size: Int,
    val priceInfo: PriceInfo
)

data class Multimedia(
    val images: List<Image>,
)

data class Image(
    val url: String
)

data class ParkingSpace(
    val hasParkingSpace: Boolean
)

data class PriceInfo(
    val price : Price
)

data class Price(
    val amount: Double,
    val currencySuffix: String
)