package com.bassem.catdemo.ui.compose.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.bassem.catdemo.R
import com.bassem.catdemo.data.models.BreedItem
import com.bassem.catdemo.data.models.Result

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), onClick: (String) -> Unit) {
    val breedsResult by viewModel.breedsList.collectAsState(initial = Result.Loading)
    val filteredBreeds = remember { mutableStateListOf<BreedItem>() }
    var selectedTab by remember { mutableIntStateOf(0) }
    var query by remember { mutableStateOf("") }
    val tabs = listOf("Cats List", "Favorites")
    LaunchedEffect(breedsResult, query) {
        if (breedsResult is Result.Success) {
            val breeds = (breedsResult as Result.Success).breedItems
            filteredBreeds.clear()
            filteredBreeds.addAll(breeds.filter {
                it.name.contains(query, ignoreCase = true)
            })
        }

    }
    Scaffold(topBar = { TopAppBar(title = { Text(text = "Cat Breeds") }) },
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, title ->
                    NavigationBarItem(
                        icon = {},
                        label = { Text(title) },
                        selected = selectedTab == index,
                        onClick = { selectedTab = index }
                    )
                }
            }
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            SearchBar(query = query, onQueryChange = { new -> query = new })

            when (breedsResult) {
                is Result.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                is Result.Success -> {
                    when (selectedTab) {
                        0 -> {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(3),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(dimensionResource(id = R.dimen.default_padding))
                            ) {
                                items(filteredBreeds) { item: BreedItem ->
                                    BreedListItem(
                                        item,
                                        onCardClick = { onClick(item.id) },
                                        onFavoriteClick = {})

                                }
                            }
                        }

                        1 -> {
                            Text(text = "Favorite not implemented yet")
                        }
                    }
                }

                is Result.Fail -> {
                    Text(
                        text = (breedsResult as Result.Fail).reasons,
                        color = Color.Red,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(
                                dimensionResource(id = R.dimen.default_padding)
                            )
                    )
                }
            }


        }

    }
}