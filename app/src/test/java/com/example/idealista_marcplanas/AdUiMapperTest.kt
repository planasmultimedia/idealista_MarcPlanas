package com.example.idealista_marcplanas

import com.example.idealista_marcplanas.data.model.Ad
import com.example.idealista_marcplanas.presentation.mappers.AdUiMapper
import com.example.idealista_marcplanas.presentation.uiModels.AdUiModel
import com.example.idealista_marcplanas.utils.AdTestData
import com.example.idealista_marcplanas.utils.StringUtils
import io.mockk.*
import org.junit.jupiter.api.*
import kotlin.test.Test
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdUiMapperTest {

    @Test
    fun `mapToUiModel correctly maps Ad to AdUiModel`() {
        val ad: Ad = AdTestData.createAdExample(
            id = "1",
            propertyType = "apartment",
            address = "address",
            neighborhood = "Downtown",
            municipality = "Cityville",
            amount = 1000.0,
            currency = "€"
        )

        val dateFavorited: Long? = null

        val result: AdUiModel = AdUiMapper.mapToUiModel(ad, dateFavorited)

        assertEquals("1", result.id)
        assertEquals("Apartment in Address", result.title)
        assertEquals("Downtown, Cityville", result.address)
        assertEquals("1,000 €", result.price)
        assertEquals("Parking included", result.parkingInfo)
        assertEquals(listOf("image1.jpg", "image2.jpg"), result.images)
        assertEquals("120 m2", result.sizeInfo)
        assertEquals("sale", result.operation)
        assertEquals("3 rooms", result.roomInfo)
        assertEquals(false, result.isFavorite)
        assertEquals(null, result.favoritedAt)

    }
}