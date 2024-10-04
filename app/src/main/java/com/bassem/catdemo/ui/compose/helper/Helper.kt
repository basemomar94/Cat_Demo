package com.bassem.catdemo.ui.compose.helper

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.bassem.catdemo.R
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CatImage(imageUrl: String?) {
    GlideImage(
        model = imageUrl,
        contentDescription = stringResource(id = R.string.image_of_cat),
        modifier = Modifier
            .fillMaxWidth()
            .height(
                dimensionResource(id = R.dimen.image_height)
            ),
        contentScale = ContentScale.Crop,
        loading = placeholder(R.drawable.loading),
        failure = placeholder(R.drawable.error)

    )
}