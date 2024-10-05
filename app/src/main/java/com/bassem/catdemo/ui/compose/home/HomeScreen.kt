package com.bassem.catdemo.ui.compose.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import androidx.navigation.NavController
import com.bassem.catdemo.R
import com.bassem.catdemo.data.models.BreedItem
import com.bassem.catdemo.data.models.Result
import com.bassem.catdemo.data.models.tabs
import com.bassem.catdemo.ui.compose.Screen
import com.bassem.catdemo.utils.Logger

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onClick: (Int) -> Unit,
    navController: NavController
) {
    val logger = Logger("HomeScreen")
    val breedsResult by viewModel.breedsList.collectAsState(initial = Result.Loading)
    val filteredBreeds = remember { mutableStateListOf<BreedItem>() }
    var selectedTab by remember { mutableIntStateOf(0) }
    var query by remember { mutableStateOf("") }

    LaunchedEffect(breedsResult, query) {
        if (breedsResult is Result.Success) {
            val breeds = (breedsResult as Result.Success).breedItems
            filteredBreeds.clear()
            filteredBreeds.addAll(breeds.filter {
                it.name.contains(query, ignoreCase = true)
            })
        }

    }
    DisposableEffect(navController) {
        val callback = NavController.OnDestinationChangedListener { _, destination, _ ->
            if (destination.route == Screen.Home.route) {
                viewModel.fetchBreedsList()
            }
        }
        navController.addOnDestinationChangedListener(callback)
        onDispose {
            navController.removeOnDestinationChangedListener(callback)
        }
    }
    Scaffold(topBar = { TopAppBar(title = { Text(text = "Cat Breeds") }) },
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, tab ->
                    NavigationBarItem(
                        icon = { Icon(imageVector = tab.icon, contentDescription = tab.title) },
                        label = { Text(tab.title) },
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
                                items(filteredBreeds, key = { it.id }) { item: BreedItem ->
                                    BreedListItem(
                                        item,
                                        onCardClick = {
                                            onClick(item.dbId)
                                            logger.d("clicked item is $item")

                                        },
                                        onFavoriteClick = {
                                            viewModel.updateFavoriteStatus(
                                                item.id,
                                                !item.isFavorite
                                            )
                                            val index = filteredBreeds.indexOf(item)
                                            val updatedItem =
                                                item.copy(isFavorite = !item.isFavorite)
                                            if (index >= 0) {
                                                filteredBreeds[index] = updatedItem
                                            }
                                        })

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