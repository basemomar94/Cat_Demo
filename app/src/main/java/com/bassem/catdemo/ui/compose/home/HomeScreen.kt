package com.bassem.catdemo.ui.compose.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bassem.catdemo.data.models.BreedItem
import com.bassem.catdemo.data.models.Result
import com.bassem.catdemo.ui.compose.Screen
import com.bassem.catdemo.ui.compose.shared.ErrorTextCompose
import com.bassem.catdemo.ui.compose.shared.LoadingIndicator
import com.bassem.catdemo.ui.compose.shared.SearchBar
import com.bassem.catdemo.utils.Logger

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onClick: (String) -> Unit,
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

    LaunchedEffect(selectedTab) {
        if (selectedTab == 1) {
            navController.navigate(Screen.Favorites.route)
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

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Cat Breeds") }) },
        bottomBar = { BottomBarCompose(selectedTab) { selectedTab = it } }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchBar(query = query, onQueryChange = { new -> query = new })

            logger.i("breedResult is $breedsResult")

            when (breedsResult) {
                is Result.Loading -> LoadingIndicator()
                is Result.Success -> {
                    val displayedBreeds =
                        if (selectedTab == 1) filteredBreeds.filter { it.isFavorite } else filteredBreeds
                    HomeGrid(
                        breeds = displayedBreeds,
                        onClick = onClick,
                        onFavoriteClick = { item ->
                            viewModel.updateFavoriteStatus(item.id, !item.isFavorite)
                            val updatedItem = item.copy(isFavorite = !item.isFavorite)
                            val index = displayedBreeds.indexOf(item)

                            if (index >= 0) {
                                filteredBreeds[index] = updatedItem
                            }

                            if (!updatedItem.isFavorite) {
                                filteredBreeds.remove(item)
                            }
                        }
                    )
                }

                is Result.Fail -> {
                    ErrorTextCompose(message = (breedsResult as Result.Fail).reasons)
                }
            }
        }
    }
}
