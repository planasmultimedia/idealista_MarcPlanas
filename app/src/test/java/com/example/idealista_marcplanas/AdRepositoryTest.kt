package com.example.idealista_marcplanas

import com.example.idealista_marcplanas.data.api.AdApi
import com.example.idealista_marcplanas.data.model.Ad
import com.example.idealista_marcplanas.data.model.Image
import com.example.idealista_marcplanas.data.model.Multimedia
import com.example.idealista_marcplanas.data.model.ParkingSpace
import com.example.idealista_marcplanas.data.model.Price
import com.example.idealista_marcplanas.data.model.PriceInfo
import com.example.idealista_marcplanas.data.repository.AdRepository
import com.example.idealista_marcplanas.utils.Response
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AdRepositoryTest {

    private val api: AdApi = mockk()
    private val repository = AdRepository(api)

    @Test
    fun `Ad model initializes correctly with all fields`() {
        val price = Price(amount = 250000.0, currencySuffix = "€")
        val priceInfo = PriceInfo(price = price)
        val images = listOf(Image(url = "image1.jpg"), Image(url = "image2.jpg"))
        val multimedia = Multimedia(images = images)
        val parkingSpace = ParkingSpace(hasParkingSpace = true)

        val ad = Ad(
            propertyCode = "PROP123",
            propertyType = "apartment",
            thumbnail = "thumbnail.jpg",
            operation = "sale",
            multimedia = multimedia,
            parkingSpace = parkingSpace,
            neighborhood = "Downtown",
            municipality = "Cityville",
            address = "123 Main Street",
            rooms = 3,
            size = 120,
            priceInfo = priceInfo
        )

        assertEquals("PROP123", ad.propertyCode)
        assertEquals("apartment", ad.propertyType)
        assertEquals("thumbnail.jpg", ad.thumbnail)
        assertEquals("sale", ad.operation)
        assertEquals(images, ad.multimedia.images)
        assertEquals(true, ad.parkingSpace?.hasParkingSpace)
        assertEquals("Downtown", ad.neighborhood)
        assertEquals("Cityville", ad.municipality)
        assertEquals("123 Main Street", ad.address)
        assertEquals(3, ad.rooms)
        assertEquals(120, ad.size)
        assertEquals(250000.0, ad.priceInfo.price.amount)
        assertEquals("€", ad.priceInfo.price.currencySuffix)
    }
}