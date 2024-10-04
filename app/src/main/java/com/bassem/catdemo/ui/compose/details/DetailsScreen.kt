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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import com.bassem.catdemo.R
import com.bassem.catdemo.ui.compose.helper.CatImage
import com.bassem.catdemo.data.models.BreedItem

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DetailsCompose(
        name = "Egyptian Cat",
        imageUrl = "",
        description = "test description",
        isFavorite = false
    ) {

    }
}

@Composable
fun DetailsScreen(breedItem: BreedItem) {
    DetailsCompose(
        name = breedItem.name,
        imageUrl = breedItem.reference_image_id,
        description = breedItem.description,
        isFavorite = false
    ) {

    }
}


@Composable
fun DetailsCompose(
    name: String,
    imageUrl: String,
    description: String,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.default_padding))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.default_padding))
            )
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { onFavoriteClick() },
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = stringResource(id = R.string.favorite_icon)
            )

        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))
        CatImage(imageUrl = imageUrl)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))
        Text(text = description, style = MaterialTheme.typography.bodyLarge)

    }

}