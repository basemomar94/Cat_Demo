package com.bassem.catdemo.ui.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.bassem.catdemo.ui.compose.details.DetailsScreen
import com.bassem.catdemo.ui.compose.favorites.FavoritesScreen
import com.bassem.catdemo.ui.compose.home.HomeScreen
import com.bassem.catdemo.data.models.BreedItem

@Composable
fun CatsApp() {
    val navController = rememberNavController()
    CatsNavHost(navController)

}

@Composable
fun CatsNavHost(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screens.Home) {
        composable<Screens.Home> {
            HomeScreen(onClick = { breedId ->
                navHostController.navigate(route = Screens.Details(breedId))
            })
        }
        composable<Screens.Details> { backStackEntry ->
            val item: BreedItem = backStackEntry.toRoute()
            DetailsScreen(item)
        }

        composable<Screens.Favorites> {
            FavoritesScreen()
        }

    }

}