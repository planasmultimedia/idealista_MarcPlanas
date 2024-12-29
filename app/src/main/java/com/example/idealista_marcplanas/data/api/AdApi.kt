package com.example.idealista_marcplanas.data.api

import com.example.idealista_marcplanas.data.model.Ad
import retrofit2.http.GET

interface AdApi {
    @GET("list.json")
    suspend fun getAds(): List<Ad>
}