package com.example.idealista_marcplanas

import com.example.idealista_marcplanas.data.api.AdApi
import com.example.idealista_marcplanas.data.model.Ad
import com.example.idealista_marcplanas.data.repository.AdRepository
import com.example.idealista_marcplanas.utils.Response
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test

class AdRepositoryTest {

    private val api: AdApi = mockk()
    private val repository = AdRepository(api)

    @Test
    fun `getAds returns success when API call is successful`() = runBlocking {
        val ads = listOf(Ad("1", "address", 1000.0, "thumbnail"))
        coEvery { api.getAds() } returns ads

        val result = repository.getAds()
        assertTrue(result is Response.Success)
    }

    @Test
    fun `getAds returns error when API call fails`() = runBlocking {
        coEvery { api.getAds() } throws Exception("Network error")

        val result = repository.getAds()
        assertTrue(result is Response.Error)
    }
}