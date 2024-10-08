package com.bassem.catdemo.ui.compose.shared

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bassem.catdemo.R

@Preview(showBackground = true)
@Composable
fun FavoriteIconPreview() {
    FavoriteIcon(isFavorite = true, modifier = Modifier)
}

@Composable
fun FavoriteIcon(
    isFavorite: Boolean,
    modifier: Modifier
) {
    Icon(
        modifier = modifier,
        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
        contentDescription = stringResource(id = if (isFavorite) R.string.favorite_icon else R.string.favorite_icon_with_border)
    )
}
