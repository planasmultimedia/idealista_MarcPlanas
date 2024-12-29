package com.example.idealista_marcplanas.data.api

import com.example.idealista_marcplanas.data.model.Ad
import com.example.idealista_marcplanas.data.model.AdDetail
import retrofit2.http.GET

interface AdApi {
    @GET("list.json")
    suspend fun getAds(): List<Ad>

    @GET("detail.json")
    suspend fun getAdDetail(): AdDetail
}