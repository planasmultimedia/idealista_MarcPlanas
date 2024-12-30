package com.example.idealista_marcplanas.utils

import com.example.idealista_marcplanas.data.model.*

object AdTestData {

    fun createAdExample(
        id: String = "PROP123",
        propertyType: String = "apartment",
        thumbnail: String = "thumbnail.jpg",
        operation: String = "sale",
        neighborhood: String = "Downtown",
        municipality: String = "Cityville",
        address: String = "123 Main Street",
        rooms: Int = 3,
        size: Int = 120,
        amount: Double = 250000.0,
        currency: String = "€",
        images: List<Image> = listOf(
            Image(url = "image1.jpg"),
            Image(url = "image2.jpg")
        )
    ): Ad {
        val price = Price(amount = amount, currencySuffix = currency)
        val priceInfo = PriceInfo(price = price)
        val multimedia = Multimedia(images = images)
        val parkingSpace = ParkingSpace(hasParkingSpace = true)

        return Ad(
            propertyCode = id,
            propertyType = propertyType,
            thumbnail = thumbnail,
            operation = operation,
            multimedia = multimedia,
            parkingSpace =parkingSpace,
            neighborhood = neighborhood,
            municipality = municipality,
            address = address,
            rooms = rooms,
            size = size,
            priceInfo = priceInfo
        )
    }

    fun createFavoriteAdEntity(
        id: String = "PROP123",
        favoritedAt: Long = System.currentTimeMillis()
    ): FavoriteAdEntity {
        return FavoriteAdEntity(
            id = id,
            favoritedAt = favoritedAt
        )
    }

    fun createAdDetailExample(
        adId: Int = 1,
        amount: Double = 300000.0,
        currency: String = "€",
        homeType: String = "apartment",
        propertyComment: String = "A cozy apartment in the heart of the city",
        images: List<Image> = listOf(
            Image(url = "image1.jpg"),
            Image(url = "image2.jpg")
        )
    ): AdDetail {
        val price = Price(amount = amount, currencySuffix = currency)
        val multimedia = Multimedia(images = images)
        val moreCharacteristics = createMoreCharacteristics()

        return AdDetail(
            adId = adId,
            priceInfo = price,
            homeType = homeType,
            propertyComment = propertyComment,
            multimedia = multimedia,
            moreCharacteristics = moreCharacteristics
        )
    }

    fun createMoreCharacteristics(
        roomNumber: Int = 3,
        bathNumber: Int = 2,
        exterior: Boolean = true,
        energyCertificationType: String = "A"
    ): MoreCharacteristics {
        return MoreCharacteristics(
            roomNumber = roomNumber,
            bathNumber = bathNumber,
            exterior = exterior,
            energyCertificationType = energyCertificationType
        )
    }
}