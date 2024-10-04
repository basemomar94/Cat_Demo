package com.bassem.catdemo.ui.compose.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.bassem.catdemo.R

@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    TextField(
        value = query, onValueChange = onQueryChange, modifier = Modifier
            .fillMaxWidth()
            .padding(
                dimensionResource(id = R.dimen.default_padding),
            )
            .background(
                color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(
                    dimensionResource(id = R.dimen.card_corner_radius)
                )
            ), leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search_icon)
            )
        }
    )


}