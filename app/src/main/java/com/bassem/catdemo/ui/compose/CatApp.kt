package com.bassem.catdemo.ui.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bassem.catdemo.ui.compose.details.DetailsScreen
import com.bassem.catdemo.ui.compose.favorites.FavoritesScreen
import com.bassem.catdemo.ui.compose.home.HomeScreen

@Composable
fun CatsApp() {
    val navController = rememberNavController()
    CatsNavHost(navController)

}

@Composable
fun CatsNavHost(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(onClick = {
                navHostController.navigate(Screen.Details.createRoute(it))
            }, navController = navHostController)
        }
        composable(route = Screen.Details.route, arguments = Screen.Details.navArguments) {
            DetailsScreen()
        }

        composable(route = Screen.Favorites.route) {
            FavoritesScreen(onClick = {
                navHostController.navigate(Screen.Details.createRoute(it))
            }, navController = navHostController)
        }

    }
}
