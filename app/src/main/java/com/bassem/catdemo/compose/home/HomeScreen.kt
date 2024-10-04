package com.bassem.catdemo.compose.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.bassem.catdemo.data.models.BreedItem
import com.bassem.catdemo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val breedsList = listOf<BreedItem>()
    Scaffold(topBar = { TopAppBar(title = { Text(text = "Cat Breeds") }) }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            SearchBar(query = "", onQueryChange = {})

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.default_padding))
            ) {
                items(breedsList) { item: BreedItem ->
                    BreedListItem(item, onClick = {})

                }
            }

        }

    }
}