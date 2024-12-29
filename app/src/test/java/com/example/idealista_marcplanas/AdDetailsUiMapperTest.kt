package com.example.idealista_marcplanas

import com.example.idealista_marcplanas.data.model.*
import com.example.idealista_marcplanas.presentation.mappers.AdDetailUiMapper
import com.example.idealista_marcplanas.presentation.uiModels.AdDetailUiModel
import com.example.idealista_marcplanas.utils.StringUtils
import io.mockk.*
import junit.framework.TestCase.assertEquals

import kotlin.test.Test

class AdDetailUiMapperTest {


    @Test
    fun `mapToUiModel correctly maps AdDetail to AdDetailUiModel`() {
        val adDetail = AdDetail(
            adId = 12345,
            priceInfo = Price(amount = 200000.0, currencySuffix = "€"),
            homeType = "apartment",
            propertyComment = "A beautiful apartment in the city center.",
            multimedia = Multimedia(images = listOf(Image("image1.jpg"), Image("image2.jpg"))),
            moreCharacteristics = MoreCharacteristics(
                roomNumber = 3,
                bathNumber = 2,
                exterior = true,
                energyCertificationType = "A"
            )
        )

        every { StringUtils.capitalizeFirstLetter("apartment") } returns "Apartment"
        every { StringUtils.capitalizeFirstLetter("A") } returns "A"

        val result: AdDetailUiModel = AdDetailUiMapper.mapToUiModel(adDetail)

        assertEquals("12345", result.id)
        assertEquals("200000 €", result.price)
        assertEquals("Apartment", result.homeType)
        assertEquals("A beautiful apartment in the city center.", result.description)
        assertEquals(listOf("image1.jpg", "image2.jpg"), result.images)
        assertEquals("3 rooms", result.rooms)
        assertEquals("2 bathrooms", result.bathrooms)
        assertEquals("Exterior", result.exterior)
        assertEquals("Energy Certigication Type: A", result.energyCertificationType)

        verify { StringUtils.capitalizeFirstLetter("apartment") }
        verify { StringUtils.capitalizeFirstLetter("A") }

        confirmVerified(StringUtils)
    }
}