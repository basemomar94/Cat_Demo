package com.bassem.catdemo.ui.compose.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bassem.catdemo.R
import com.bassem.catdemo.data.models.BreedItem
import com.bassem.catdemo.ui.compose.shared.BreedImage
import com.bassem.catdemo.ui.compose.shared.FavoriteIcon
import com.bassem.catdemo.utils.getImageUrl

@Preview(showBackground = true)
@Composable
fun PreviewBreedItem() {
    BreedViewItemCompose(
        name = "Sphinx Cat",
        imageUrl = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg",
        isFavorite = false,
        onCardClick = {},
        onFavoriteClick = {}
    )
}

@Composable
fun BreedListItem(breedItem: BreedItem, onCardClick: () -> Unit, onFavoriteClick: () -> Unit) {
    with(breedItem) {
        BreedViewItemCompose(
            name = name,
            imageUrl = reference_image_id.getImageUrl(),
            isFavorite = isFavorite,
            onCardClick = onCardClick,
            onFavoriteClick = onFavoriteClick
        )
    }

}

@Composable
fun BreedViewItemCompose(
    name: String,
    imageUrl: String?,
    isFavorite: Boolean,
    onCardClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Card(
        onClick = onCardClick,
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.card_side_margin))
            .shadow(
                elevation = dimensionResource(
                    id = R.dimen.small_padding,
                ), shape = RoundedCornerShape(8.dp)
            )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.fillMaxWidth()) {
                BreedImage(
                    imageUrl = imageUrl, modifier = Modifier
                        .fillMaxWidth()
                        .height(
                            dimensionResource(id = R.dimen.image_height)
                        )
                )
                FavoriteIcon(isFavorite = isFavorite, modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.small_padding))
                    .align(Alignment.TopEnd)
                    .clickable { onFavoriteClick() })
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