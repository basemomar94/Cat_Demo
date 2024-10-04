package com.bassem.catdemo.ui.compose.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.bassem.catdemo.R
import com.bassem.catdemo.ui.compose.helper.CatImage
import com.bassem.catdemo.data.models.BreedItem
import com.bassem.catdemo.utils.Logger
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@Preview(showBackground = true)
@Composable
fun PreviewBreedItem() {
    BreedViewItem(
        name = "Sphinx Cat",
        imageUrl = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg",
        isFavorite = false,
        onCardClick = {},
        onFavoriteClick = {}
    )
}

@Composable
fun BreedListItem(breedItem: BreedItem, onCardClick: () -> Unit, onFavoriteClick: () -> Unit) {
    val imageUrl = "https://cdn2.thecatapi.com/images/${breedItem.reference_image_id}.jpg"
    Logger("BreedListItem").d("url $imageUrl")
    BreedViewItem(
        name = breedItem.name,
        imageUrl = imageUrl,
        isFavorite = breedItem.isFavorite,
        onCardClick = onCardClick,
        onFavoriteClick = onFavoriteClick
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BreedViewItem(
    name: String,
    imageUrl: String?,
    isFavorite: Boolean,
    onCardClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Card(
        onClick = onCardClick,
        modifier = Modifier.padding(dimensionResource(id = R.dimen.card_side_margin))
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.fillMaxWidth()) {
                CatImage(imageUrl =imageUrl)
                Icon(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.small_padding))
                        .align(Alignment.TopEnd)
                        .clickable { onFavoriteClick() },
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.favorite_icon),
                )
            }
            Text(
                text = name,
                textAlign = TextAlign.Center,
                maxLines = 1,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.default_padding))
            )
        }
    }


}