package com.example.idealista_marcplanas.presentation.adsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.idealista_marcplanas.R
import com.example.idealista_marcplanas.presentation.uiModels.AdUiModel
import com.example.idealista_marcplanas.utils.DateUtils

class AdAdapter(
    private val onAdClicked: (AdUiModel) -> Unit,
    private val onFavLiked: (AdUiModel) -> Unit
) : ListAdapter<AdUiModel, AdAdapter.AdViewHolder>(AdDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ad, parent, false)
        return AdViewHolder(view, onAdClicked, onFavLiked)
    }

    override fun onBindViewHolder(holder: AdViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(holder: AdViewHolder, position: Int, payloads: MutableList<Any>) {
        val ad = getItem(position)
        if (payloads.isNotEmpty()) {
            payloads.forEach { payload ->
                when (payload) {
                    "FAVORITE_UPDATE" -> holder.updateFavoriteState(ad)
                }
            }
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    class AdViewHolder(
        itemView: View,
        private val onAdClicked: (AdUiModel) -> Unit,
        private val onFavLiked: (AdUiModel) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val imageSlider: ViewPager2 = itemView.findViewById(R.id.imageSlider)
        private val sliderAdapter = SliderAdapter(emptyList())

        private val header: TextView = itemView.findViewById(R.id.header)
        private val address: TextView = itemView.findViewById(R.id.address)
        private val price: TextView = itemView.findViewById(R.id.price)
        private val parkingInfo: TextView = itemView.findViewById(R.id.parkingInfo)
        private val roomInfo: TextView = itemView.findViewById(R.id.roomInfo)
        private val sizeInfo: TextView = itemView.findViewById(R.id.sizeInfo)
        private val heartIcon: ImageView = itemView.findViewById(R.id.heartIcon)
        private val dateText: TextView = itemView.findViewById(R.id.favoriteDate)

        init {
            imageSlider.adapter = sliderAdapter
        }

        fun bind(ad: AdUiModel) {
            sliderAdapter.updateImages(ad.images)

            header.text = ad.title
            address.text = ad.address
            price.text = ad.price
            roomInfo.text = ad.roomInfo
            sizeInfo.text = ad.sizeInfo

            parkingInfo.apply {
                visibility = if (ad.parkingInfo.isEmpty()) View.GONE else View.VISIBLE
                text = ad.parkingInfo
            }

            updateFavoriteState(ad)

            heartIcon.setOnClickListener { onFavLiked(ad) }

            itemView.setOnClickListener {
                onAdClicked(ad)
            }
        }

        fun updateFavoriteState(ad: AdUiModel) {
            heartIcon.setImageResource(
                if (ad.isFavorite) R.drawable.ic_filled_heart else R.drawable.ic_heart_outline
            )

            dateText.apply {
                visibility = if (ad.isFavorite) View.VISIBLE else View.GONE
                text = ad.favoritedAt?.let { DateUtils.formatLongToReadableDate(it) }
            }
        }
    }
}