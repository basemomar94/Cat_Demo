package com.bassem.catdemo.compose.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.bassem.catdemo.R
import com.bassem.catdemo.data.models.BreedItem
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Preview(showBackground = true)
@Composable
fun PreviewBreedItem() {
    BreedViewItem(
        name = "Sphinx Cat",
        imageUrl = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg",
        onClick = {}
    )
}

@Composable
fun BreedListItem(breedItem: BreedItem, onClick: () -> Unit) {
    BreedViewItem(name = breedItem.name, imageUrl = breedItem.image.url, onClick = onClick)
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BreedViewItem(name: String, imageUrl: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.padding(dimensionResource(id = R.dimen.card_side_margin))
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            GlideImage(
                model = imageUrl,
                contentDescription = stringResource(id = R.string.image_of_cat),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        dimensionResource(id = R.dimen.image_height)
                    ),
                contentScale = ContentScale.Crop
            )
            Text(
                text = name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.default_padding))
            )
        }
    }


}