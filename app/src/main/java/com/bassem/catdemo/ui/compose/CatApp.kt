package com.bassem.catdemo.ui.compose

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.bassem.catdemo.ui.compose.details.DetailsScreen
import com.bassem.catdemo.ui.compose.favorites.FavoritesScreen
import com.bassem.catdemo.ui.compose.home.HomeScreen
import com.bassem.catdemo.data.models.BreedItem
import com.bassem.catdemo.ui.compose.details.DetailsViewModel

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
            })
        }
        composable(route = Screen.Details.route, arguments = Screen.Details.navArguments) {
            DetailsScreen( )
        }

    }
}
