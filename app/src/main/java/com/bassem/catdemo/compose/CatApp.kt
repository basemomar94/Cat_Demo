package com.bassem.catdemo.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bassem.catdemo.compose.details.DetailsScreen
import com.bassem.catdemo.compose.favorites.FavoritesScreen
import com.bassem.catdemo.compose.home.HomeScreen

@Composable
fun CatsApp() {
    val navController = rememberNavController()
    CatsNavHost(navController)

}

@Composable
fun CatsNavHost(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route) {
            HomeScreen()
        }
        composable(route = Screens.Details.route) {
            DetailsScreen()
        }

        composable(route = Screens.Favorites.route) {
            FavoritesScreen()
        }

    }

}