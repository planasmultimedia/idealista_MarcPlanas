package com.example.idealista_marcplanas.presentation.mappers

import com.example.idealista_marcplanas.data.model.AdDetail
import com.example.idealista_marcplanas.presentation.mappers.AdUiMapper.formatPrice
import com.example.idealista_marcplanas.presentation.uiModels.AdDetailUiModel
import com.example.idealista_marcplanas.utils.StringUtils

object AdDetailUiMapper {
    fun mapToUiModel(ad: AdDetail): AdDetailUiModel {
        return AdDetailUiModel(
            id = ad.adId,
            price = formatPrice(ad.priceInfo.amount, ad.priceInfo.currencySuffix),
            homeType = StringUtils.capitalizeFirstLetter(ad.homeType),
            description = ad.propertyComment,
            images = ad.multimedia.images.map { it.url },
            rooms = ad.moreCharacteristics.roomNumber.toString() + " rooms",
            bathrooms = ad.moreCharacteristics.bathNumber.toString() + " bathrooms",
            exterior = if (ad.moreCharacteristics.exterior) "Exterior" else "",
            energyCertificationType = "Energy Certigication Type: " + StringUtils.capitalizeFirstLetter(ad.moreCharacteristics.energyCertificationType)
        )
    }
}