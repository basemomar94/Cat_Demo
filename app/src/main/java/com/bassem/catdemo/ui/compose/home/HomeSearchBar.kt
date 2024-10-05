package com.bassem.catdemo.ui.compose.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bassem.catdemo.R

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    HomeSearchBar("", {})
}

@Composable
fun HomeSearchBar(query: String, onQueryChange: (String) -> Unit) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                dimensionResource(id = R.dimen.default_padding),
            )
            .background(
                color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(
                    dimensionResource(id = R.dimen.card_corner_radius)
                )
            ),
        placeholder = { Text(text = stringResource(id = R.string.search_place_holder)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search_icon)
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = stringResource(id = R.string.clear)
                    )
                }
            }
        },
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius)),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent, // Remove focused underline
            unfocusedIndicatorColor = Color.Transparent, // Remove unfocused underline
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        ),
    )


}