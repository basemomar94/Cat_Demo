package com.bassem.catdemo.ui.compose.details

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.bassem.catdemo.utils.Logger
import com.bassem.catdemo.utils.getImageUrl

@Composable
fun DetailsScreen(viewModel: DetailsViewModel = hiltViewModel()) {
    val logger = Logger("DetailsScreen")
    val breedItem by viewModel.breed.collectAsState(initial = null)
    LaunchedEffect(key1 = Unit) {
        viewModel.getBreedById()
    }
    when (val item = breedItem) {
        null -> {
            CircularProgressIndicator()
        }

        else -> {
            logger.d("breed item is $breedItem")
            with(item) {
                var isFavoriteState by remember { mutableStateOf(isFavorite) }
                DetailsCompose(
                    name = name,
                    imageUrl = reference_image_id.getImageUrl(),
                    description = description,
                    origin = origin,
                    temperament = temperament,
                    isFavorite = isFavoriteState,
                    onFavoriteClick = {
                        viewModel.updateFavoriteStatus(id, !isFavorite)
                        isFavoriteState = !isFavoriteState
                    }
                )
            }

        }
    }

}