package com.example.idealista_marcplanas.presentation.mappers

import com.example.idealista_marcplanas.data.model.Ad
import com.example.idealista_marcplanas.presentation.uiModels.AdUiModel
import java.text.NumberFormat
import java.util.Locale

object AdUiMapper {
    fun mapToUiModel(ad: Ad): AdUiModel {
        return AdUiModel(
            id = ad.propertyCode,
            address = formatAddress(ad.address),
            price = formatPrice(ad.price),
            thumbnail = ad.thumbnail
        )
    }

    private fun formatAddress(address: String): String {
        return address.lowercase().replaceFirstChar { it.uppercase() }
    }

    private fun formatPrice(price: Double): String {
        val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
        return "${numberFormat.format(price)} €"
    }
}