package com.bassem.catdemo.ui.compose.favorites

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
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bassem.catdemo.R
import com.bassem.catdemo.data.models.BreedItem
import com.bassem.catdemo.data.models.Result
import com.bassem.catdemo.ui.compose.Screen
import com.bassem.catdemo.ui.compose.home.BottomBarCompose
import com.bassem.catdemo.ui.compose.home.HomeGrid
import com.bassem.catdemo.ui.compose.shared.SearchBar
import com.bassem.catdemo.ui.compose.shared.ErrorTextCompose
import com.bassem.catdemo.ui.compose.shared.LoadingIndicator
import com.bassem.catdemo.utils.Logger
import com.bassem.catdemo.utils.getAverageSpan

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    onClick: (String) -> Unit,
    navController: NavController
) {
    val logger = Logger("FavoritesScreen")
    val favoritesResult by viewModel.favoritesList.collectAsState(initial = Result.Loading)
    var query by remember { mutableStateOf("") }
    var averageLifespan by remember { mutableStateOf<Double?>(null) }
    var selectedTab by remember { mutableIntStateOf(1) }
    val filteredFavorites = remember { mutableStateListOf<BreedItem>() }

    LaunchedEffect(favoritesResult) {
        when (favoritesResult) {
            is Result.Loading -> {
                logger.i("loading favorites")
            }

            is Result.Fail -> {
                logger.e("Error fetching favorites: ${(favoritesResult as Result.Fail).reasons}")
            }

            is Result.Success -> {
                val favorites = (favoritesResult as Result.Success).breedItems
                filteredFavorites.clear()
                filteredFavorites.addAll(favorites)
                averageLifespan = filteredFavorites.getAverageSpan()
            }
        }
    }

    LaunchedEffect(selectedTab) {
        if (selectedTab == 0) {
            navController.popBackStack()
        }
    }

    DisposableEffect(navController) {
        val callback = NavController.OnDestinationChangedListener { _, destination, _ ->
            if (destination.route == Screen.Favorites.route) {
                viewModel.fetchFavorites()
            }
        }
        navController.addOnDestinationChangedListener(callback)
        onDispose {
            navController.removeOnDestinationChangedListener(callback)
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Favorite Cat Breeds") }) },
        bottomBar = { BottomBarCompose(selectedTab) { selectedTab = it } }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchBar(query = query, onQueryChange = { new -> query = new })

            when (favoritesResult) {
                is Result.Loading -> LoadingIndicator()
                is Result.Success -> {
                    val displayedFavorites = filteredFavorites.filter {
                        it.name.contains(query, ignoreCase = true)
                    }

                    if (displayedFavorites.isEmpty()) {
                        ErrorTextCompose(message = stringResource(R.string.no_favorites))
                    } else {
                        LifeSpanText(averageLifespan)
                        HomeGrid(
                            breeds = displayedFavorites,
                            onClick = onClick,
                            onFavoriteClick = { item ->
                                viewModel.removeFavorite(item.id)
                                filteredFavorites.remove(item)
                                averageLifespan = filteredFavorites.getAverageSpan()
                                logger.d("Removed from favorites: ${item.name}")
                            }
                        )
                    }
                }

                is Result.Fail -> {
                    ErrorTextCompose(message = (favoritesResult as Result.Fail).reasons)
                }
            }
        }
    }
}


