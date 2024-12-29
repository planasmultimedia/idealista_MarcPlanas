package com.example.idealista_marcplanas.presentation.adsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.idealista_marcplanas.R
import com.example.idealista_marcplanas.data.model.Ad
import com.example.idealista_marcplanas.presentation.uiModels.AdUiModel

class AdAdapter(
    private val onAdClicked: (AdUiModel) -> Unit
) : ListAdapter<AdUiModel, AdAdapter.AdViewHolder>(AdDiffCallback()) {

    inner class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnailImageView)
        private val addressTextView: TextView = itemView.findViewById(R.id.addressTextView)
        private val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)

        fun bind(ad: AdUiModel) {

            thumbnailImageView.load(ad.thumbnail) {
                placeholder(R.drawable.placeholder_house)
                error(R.drawable.error_image)
            }

            addressTextView.text = ad.address
            priceTextView.text = ad.price

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