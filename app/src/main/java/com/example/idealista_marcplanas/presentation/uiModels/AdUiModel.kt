package com.example.idealista_marcplanas.presentation.uiModels

data class AdUiModel(
    val id: String,
    val title : String,
    val address: String,
    val price: String,
    val parkingInfo : String,
    val images: List<String>,
    val roomInfo: String,
    val sizeInfo: String,
    val operation : String,
    val isFavorite: Boolean,
    val favoritedAt: Long? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AdUiModel

        if (id != other.id) return false
        if (title != other.title) return false
        if (address != other.address) return false
        if (price != other.price) return false
        if (parkingInfo != other.parkingInfo) return false
        if (images != other.images) return false
        if (roomInfo != other.roomInfo) return false
        if (sizeInfo != other.sizeInfo) return false
        if (operation != other.operation) return false
        if (isFavorite != other.isFavorite) return false
        if (favoritedAt != other.favoritedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + address.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + parkingInfo.hashCode()
        result = 31 * result + images.hashCode()
        result = 31 * result + roomInfo.hashCode()
        result = 31 * result + sizeInfo.hashCode()
        result = 31 * result + operation.hashCode()
        result = 31 * result + isFavorite.hashCode()
        result = 31 * result + (favoritedAt?.hashCode() ?: 0)
        return result
    }
}