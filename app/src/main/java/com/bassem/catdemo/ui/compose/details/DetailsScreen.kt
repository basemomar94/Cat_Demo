package com.bassem.catdemo.ui.compose.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.bassem.catdemo.R
import com.bassem.catdemo.ui.compose.helper.CatImage
import com.bassem.catdemo.data.models.BreedItem
import com.bassem.catdemo.utils.Logger
import com.bassem.catdemo.utils.getImageUrl
import dagger.hilt.android.lifecycle.HiltViewModel
@Composable
fun DetailsScreen(viewModel: DetailsViewModel = hiltViewModel()) {
    val breedItem by viewModel.breed.collectAsState(initial = null)
    LaunchedEffect(key1 = Unit) {
        viewModel.getBreedById()
    }
    when (val item = breedItem) {
        null -> {
            CircularProgressIndicator()
        }

        else -> {
            with(item) {
                DetailsCompose(
                    name = name,
                    imageUrl = reference_image_id.getImageUrl(),
                    description = description,
                    origin = origin,
                    temperament = temperament,
                    isFavorite = isFavorite,
                    onFavoriteClick = {}
                )
            }

        }
    }

}