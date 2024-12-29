package com.example.idealista_marcplanas.presentation.adsList

import androidx.recyclerview.widget.DiffUtil
import com.example.idealista_marcplanas.data.model.Ad
import com.example.idealista_marcplanas.presentation.uiModels.AdUiModel

class AdDiffCallback : DiffUtil.ItemCallback<AdUiModel>() {
    override fun areItemsTheSame(oldItem: AdUiModel, newItem: AdUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AdUiModel, newItem: AdUiModel): Boolean {
        return oldItem == newItem
    }
}