package com.example.idealista_marcplanas.data.repository

import com.example.idealista_marcplanas.data.api.AdApi
import com.example.idealista_marcplanas.data.local.FavoritesAdDao
import com.example.idealista_marcplanas.utils.AdTestData
import com.example.idealista_marcplanas.utils.MainCoroutineRule
import com.example.idealista_marcplanas.utils.Response
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.*

@OptIn(ExperimentalCoroutinesApi::class)
class AdRepositoryTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val adApi: AdApi = mockk()
    private val favoritesDao: FavoritesAdDao = mockk()
    private lateinit var repository: AdRepository

    @Before
    fun setup() {
        repository = AdRepository(adApi, favoritesDao)
    }

    @After
    fun teardown() {
        clearAllMocks()
    }

    // ---- API Tests ----

    @Test
    fun `getAds returns success when API call is successful`() = runTest {
        val ads = listOf(AdTestData.createAdExample())
        coEvery { adApi.getAds() } returns ads

        val result = repository.getAds()

        assertTrue(result is Response.Success)
        assertEquals(ads, (result as Response.Success).data)
        coVerify { adApi.getAds() }
    }

    @Test
    fun `getAds returns error when API call fails`() = runTest {
        coEvery { adApi.getAds() } throws Exception("Network Error")

        val result = repository.getAds()

        assertTrue(result is Response.Error)
        assertEquals("Failed to fetch ads: Network Error", (result as Response.Error).message)
        coVerify { adApi.getAds() }
    }

    @Test
    fun `getAdDetail returns success when API call is successful`() = runTest {
        val adDetail = AdTestData.createAdDetailExample()
        coEvery { adApi.getAdDetail() } returns adDetail

        val result = repository.getAdDetail()

        assertTrue(result is Response.Success)
        assertEquals(adDetail, (result as Response.Success).data)
        coVerify { adApi.getAdDetail() }
    }

    @Test
    fun `getAdDetail returns error when API call fails`() = runTest {
        coEvery { adApi.getAdDetail() } throws Exception("Detail Error")

        val result = repository.getAdDetail()

        assertTrue(result is Response.Error)
        assertEquals("Failed to fetch ad detail: Detail Error", (result as Response.Error).message)
        coVerify { adApi.getAdDetail() }
    }

    // ---- DAO Tests ----

    @Test
    fun `getFavorites returns a flow of favorite ads`() = runTest {
        val favorites = listOf(AdTestData.createFavoriteAdEntity())
        every { favoritesDao.getAllFavorites() } returns flowOf(favorites)

        val result = repository.getFavorites().first()

        assertEquals(favorites, result)
        verify { favoritesDao.getAllFavorites() }
    }

    @Test
    fun `addToFavorites adds an ad to favorites`() = runTest {
        val adId = "PROP123"
        coEvery { favoritesDao.addToFavorites(any()) } just Runs

        repository.addToFavorites(adId)

        coVerify {
            favoritesDao.addToFavorites(
                match { it.id == adId && it.favoritedAt > 0 }
            )
        }
    }

    @Test
    fun `addToFavorites handles DAO exception`() = runTest {
        val adId = "PROP123"
        coEvery { favoritesDao.addToFavorites(any()) } throws Exception("DB Error")

        try {
            repository.addToFavorites(adId)
            fail("Exception should have been thrown")
        } catch (e: Exception) {
            assertEquals("DB Error", e.message)
        }
    }

    @Test
    fun `removeFromFavorites removes an ad from favorites`() = runTest {
        val adId = "PROP123"
        coEvery { favoritesDao.removeFromFavorites(adId) } just Runs

        repository.removeFromFavorites(adId)

        coVerify { favoritesDao.removeFromFavorites(adId) }
    }

    @Test
    fun `removeFromFavorites handles DAO exception`() = runTest {
        val adId = "PROP123"
        coEvery { favoritesDao.removeFromFavorites(adId) } throws Exception("DB Error")

        try {
            repository.removeFromFavorites(adId)
            fail("Exception should have been thrown")
        } catch (e: Exception) {
            assertEquals("DB Error", e.message)
        }
    }
}
