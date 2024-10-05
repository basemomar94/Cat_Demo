package com.bassem.catdemo.ui.compose.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.bassem.catdemo.R
import com.bassem.catdemo.data.models.BreedItem


@Composable
fun HomeGrid(
    breeds: List<BreedItem>,
    onClick: (String) -> Unit,
    onFavoriteClick: (BreedItem) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.default_padding))
    ) {
        items(breeds, key = { it.id }) { item ->
            BreedListItem(
                item,
                onCardClick = { onClick(item.id) },
                onFavoriteClick = { onFavoriteClick(item) }
            )
        }
    }

    }
