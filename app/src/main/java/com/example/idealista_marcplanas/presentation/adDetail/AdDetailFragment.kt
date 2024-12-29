package com.example.idealista_marcplanas.presentation.adDetail

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil3.compose.AsyncImage
import com.example.idealista_marcplanas.R
import com.example.idealista_marcplanas.data.model.AdDetail
import com.example.idealista_marcplanas.presentation.adsList.SliderAdapter
import com.example.idealista_marcplanas.presentation.uiModels.AdDetailUiModel
import com.example.idealista_marcplanas.utils.Response
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jetbrains.annotations.Async

@AndroidEntryPoint
class AdDetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel: AdDetailViewModel by viewModels()
    private lateinit var progressBar: ProgressBar
    private lateinit var composeView : androidx.compose.ui.platform.ComposeView
    private lateinit var imageSlider : androidx.viewpager2.widget.ViewPager2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        progressBar = view.findViewById(R.id.progressBar)
        composeView = view.findViewById(R.id.composeView)
        imageSlider = view.findViewById(R.id.imageSlider)
        observeState()
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.detailState.collectLatest { state ->
                when (state) {
                    is Response.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is Response.Success -> {
                        progressBar.visibility = View.GONE

                        composeView.setContent {
                            imageSlider.adapter = SliderAdapter(state.data.images)
                            MaterialTheme {
                                AdDetailScreen(state.data)
                            }
                        }
                    }
                    is Response.Error -> {
                        progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }
}

