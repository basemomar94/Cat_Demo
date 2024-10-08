package com.bassem.catdemo.ui.compose.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bassem.catdemo.R
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@Preview(showBackground = true)
@Composable
fun BreedItemPreview() {
    BreedImage(imageUrl = "test", modifier = Modifier)
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BreedImage(imageUrl: String?, modifier: Modifier) {
    GlideImage(
        model = imageUrl,
        contentDescription = stringResource(id = R.string.image_of_cat),
        modifier = modifier,
        contentScale = ContentScale.Crop,
        loading = placeholder(R.drawable.loading),
        failure = placeholder(R.drawable.error),

        )
}