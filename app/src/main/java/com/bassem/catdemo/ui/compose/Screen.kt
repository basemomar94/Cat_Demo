package com.bassem.catdemo.ui.compose

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    data object Home : Screen("home")

    data object Details : Screen(
        route = "details/{breedId}",
        navArguments = listOf(navArgument("breedId") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(breedId: String) = "details/${breedId}"
    }

}

