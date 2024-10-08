package com.bassem.catdemo.ui.compose.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bassem.catdemo.R
import com.bassem.catdemo.ui.compose.home.BackButton
import com.bassem.catdemo.ui.compose.shared.BreedImage
import com.bassem.catdemo.ui.compose.shared.FavoriteIcon

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DetailsCompose(
        name = "Egyptian Cat",
        imageUrl = "",
        description = "test description",
        origin = "Egypt",
        temperament = "Loyal - Social - Funny",
        isFavorite = false,
        onBackClick = {},
        onFavoriteClick = {}
    )
}

@Composable
fun DetailsCompose(
    name: String,
    imageUrl: String,
    description: String,
    origin: String,
    temperament: String,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.default_padding))
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            BackButton(onBackClick = {onBackClick()})
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.default_padding))
                    .align(Alignment.CenterVertically)
            )

            FavoriteIcon(modifier = Modifier
                .clickable { onFavoriteClick() }
                .align(Alignment.CenterVertically)
                .size(35.dp), isFavorite = isFavorite)
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))
        BreedImage(
            imageUrl = imageUrl, modifier = Modifier
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.default_padding)))
                .shadow(elevation = 4.dp)
                .height(dimensionResource(id = R.dimen.details_image_height))
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))
        Text(text = description, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))
        Text(
            text = "Origin: $origin",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(4.dp)
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))

        ChipCompose(temperament = temperament)

    }

}