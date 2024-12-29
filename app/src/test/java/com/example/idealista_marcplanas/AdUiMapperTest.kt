package com.example.idealista_marcplanas

import com.example.idealista_marcplanas.data.model.Ad
import com.example.idealista_marcplanas.presentation.mappers.AdUiMapper
import junit.framework.TestCase.assertEquals
import kotlin.test.Test


class AdUiMapperTest {

    @Test
    fun `mapToUiModel correctly maps Ad to AdUiModel`() {
        val ad = Ad("1", "thumbnail", 1000.00, "address")

        val result = AdUiMapper.mapToUiModel(ad)

        assertEquals("1", result.id)
        assertEquals("Address", result.address)
        assertEquals("1,000 €", result.price)
        assertEquals("thumbnail", result.thumbnail)
    }
}