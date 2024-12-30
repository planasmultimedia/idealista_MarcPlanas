package com.example.idealista_marcplanas.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_ads")
data class FavoriteAdEntity(
    @PrimaryKey val id: String,
    val favoritedAt: Long
)