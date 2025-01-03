package com.bassem.catdemo.ui.compose.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bassem.catdemo.ui.compose.shared.LoadingIndicator
import com.bassem.catdemo.utils.Logger
import com.bassem.catdemo.utils.getImageUrl

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(), navController: NavController, breedId: String?
) {
    val logger = Logger("DetailsScreen")
    val breedItem by viewModel.breed.collectAsState(initial = null)
    LaunchedEffect(key1 = Unit) {
        viewModel.getBreedById(breedId)
    }
    Scaffold { defaultPadding ->
        Column(modifier = Modifier.padding(defaultPadding)) {
            when (val item = breedItem) {

                null -> {
                    LoadingIndicator()
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
                            },
                            onBackClick = { navController.popBackStack() }
                        )
                    }

                }
            }
        }
    }


}