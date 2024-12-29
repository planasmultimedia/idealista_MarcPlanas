package com.example.idealista_marcplanas.presentation.adDetail

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
import coil3.compose.AsyncImage
import com.example.idealista_marcplanas.presentation.uiModels.AdDetailUiModel

@Composable
fun AdDetailScreen(detail: AdDetailUiModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(top = 24.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                Column (Modifier.padding(horizontal = 16.dp)) {
                    Text(text = detail.price,
                        style = MaterialTheme.typography.headlineMedium)

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(text = detail.homeType,
                        style = MaterialTheme.typography.headlineSmall)

                    Spacer(modifier = Modifier.size(16.dp))

                    Row {
                        Text(text = detail.rooms,
                            style = MaterialTheme.typography.bodyLarge)

                        Spacer(modifier = Modifier.size(8.dp))

                        Text(text = detail.bathrooms,
                            style = MaterialTheme.typography.bodyLarge)
                    }

                    Spacer(modifier = Modifier.size(8.dp))

                    if (detail.exterior.isNotEmpty()) {
                        Text(text = detail.exterior,
                            style = MaterialTheme.typography.bodyLarge)

                        Spacer(modifier = Modifier.size(8.dp))
                    }

                    Text(text = detail.energyCertificationType,
                        style = MaterialTheme.typography.bodyLarge)

                    Spacer(modifier = Modifier.size(16.dp))

                    Text(text = detail.description,
                        style = MaterialTheme.typography.bodyMedium)
                }
            }

            items(
                count = detail.images.size,
                key = { index -> detail.images[index] }
            ) {
                AsyncImage(
                    model = detail.images[it],
                    contentDescription = "Image",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

    }
}
