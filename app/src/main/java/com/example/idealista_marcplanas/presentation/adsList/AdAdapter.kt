package com.example.idealista_marcplanas.presentation.adsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.idealista_marcplanas.R
import com.example.idealista_marcplanas.presentation.uiModels.AdUiModel

class AdAdapter(
    private val onAdClicked: (AdUiModel) -> Unit
) : ListAdapter<AdUiModel, AdAdapter.AdViewHolder>(AdDiffCallback()) {

    inner class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageSlider: ViewPager2 = itemView.findViewById(R.id.imageSlider)
        private val header: TextView = itemView.findViewById(R.id.header)
        private val address: TextView = itemView.findViewById(R.id.address)
        private val price: TextView = itemView.findViewById(R.id.price)
        private val parkingInfo: TextView = itemView.findViewById(R.id.parkingInfo)
        private val roomInfo: TextView = itemView.findViewById(R.id.roomInfo)
        private val sizeInfo: TextView = itemView.findViewById(R.id.sizeInfo)
        private val priceTextView: TextView = itemView.findViewById(R.id.price)

        fun bind(ad: AdUiModel) {
            imageSlider.adapter = SliderAdapter(ad.images)
            header.text = ad.title
            address.text = ad.address
            price.text = ad.price
            roomInfo.text = ad.roomInfo
            sizeInfo.text = ad.sizeInfo
            priceTextView.text = ad.price

            if (ad.parkingInfo.isEmpty()) {
                parkingInfo.visibility = View.GONE
            } else {
                parkingInfo.text = ad.parkingInfo
            }

            itemView.setOnClickListener {
                onAdClicked(ad)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ad, parent, false)
        return AdViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdViewHolder, position: Int) {
        val ad = getItem(position)
        holder.bind(ad)
    }
}