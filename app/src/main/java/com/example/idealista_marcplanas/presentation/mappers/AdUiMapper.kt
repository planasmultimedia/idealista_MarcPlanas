package com.example.idealista_marcplanas.presentation.mappers

import com.example.idealista_marcplanas.data.model.Ad
import com.example.idealista_marcplanas.data.model.PriceInfo
import com.example.idealista_marcplanas.presentation.uiModels.AdUiModel
import com.example.idealista_marcplanas.utils.StringUtils.capitalizeFirstLetter
import java.text.NumberFormat
import java.util.Locale

object AdUiMapper {
    fun mapToUiModel(ad: Ad): AdUiModel {
        return AdUiModel(
            id = ad.propertyCode,
            title = capitalizeFirstLetter(ad.propertyType) + " in " + capitalizeFirstLetter(ad.address),
            address = capitalizeFirstLetter(ad.neighborhood) + ", " + capitalizeFirstLetter(ad.municipality),
            price = formatPrice(ad.priceInfo.price.amount, ad.priceInfo.price.currencySuffix),
            parkingInfo = if (ad.parkingSpace?.hasParkingSpace == true) "Parking included" else "",
            images = listOf(ad.thumbnail) + ad.multimedia.images.map { it.url },
            sizeInfo = "${ad.size} m2",
            operation = ad.operation,
            roomInfo = "${ad.rooms} rooms",
        )
    }

    fun formatPrice(amount: Double, suffix : String): String {
        val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
        return "${numberFormat.format(amount)} ${suffix}"
    }
}