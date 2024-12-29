package com.example.idealista_marcplanas

import com.example.idealista_marcplanas.data.model.Ad
import com.example.idealista_marcplanas.data.model.Multimedia
import com.example.idealista_marcplanas.data.model.Price
import com.example.idealista_marcplanas.data.model.PriceInfo
import com.example.idealista_marcplanas.presentation.mappers.AdUiMapper
import com.example.idealista_marcplanas.presentation.mappers.AdUiMapper.formatPrice
import com.example.idealista_marcplanas.presentation.uiModels.AdUiModel
import com.example.idealista_marcplanas.utils.StringUtils
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test


class AdUiMapperTest {

    @BeforeEach
    fun setup() {
        mockkStatic(StringUtils::class)
        every { formatPrice(1000.0, "€") } returns "1,000 €"
        every { StringUtils.capitalizeFirstLetter("address") } returns "Address"
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `mapToUiModel correctly maps Ad to AdUiModel`() {
        val price = Price(amount = 1000.0, currencySuffix = "€")
        val priceInfo = PriceInfo(price = price)
        val ad = Ad(
            propertyCode = "1",
            propertyType = "apartment",
            thumbnail = "thumbnail.jpg",
            operation = "sale",
            multimedia = Multimedia(images = emptyList()),
            parkingSpace = null,
            neighborhood = "Downtown",
            municipality = "Cityville",
            address = "address",
            rooms = 2,
            size = 100,
            priceInfo = priceInfo
        )

        val result: AdUiModel = AdUiMapper.mapToUiModel(ad)

        assertEquals("1", result.id)
        assertEquals("Downtown, Cityville", result.address)
        assertEquals("1,000 €", result.price)
        assertEquals("thumbnail.jpg", result.images[0])
    }
}