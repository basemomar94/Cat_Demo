package com.bassem.catdemo.ui.compose.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.bassem.catdemo.data.models.BreedItem
import com.bassem.catdemo.R
import androidx.compose.foundation.lazy.grid.items


@Composable
fun HomeGrid(
    breeds: List<BreedItem>,
    selectedTab: Int,
    onClick: (Int) -> Unit,
    onFavoriteClick: (BreedItem) -> Unit
) {
    when (selectedTab) {
        0 -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.default_padding))
            ) {
                items(breeds, key = { it.id }) { item ->
                    BreedListItem(
                        item,
                        onCardClick = { onClick(item.dbId) },
                        onFavoriteClick = { onFavoriteClick(item) }
                    )
                }
            }
        }
        1 -> {
            Text(text = "Favorite not implemented yet")
        }
    }
}