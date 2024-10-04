package com.bassem.catdemo.compose

import androidx.navigation.NamedNavArgument

sealed class Screens(val route: String, val arguments: List<NamedNavArgument>) {
    data object Home : Screens("Home", emptyList())
    data object Details : Screens("Details", emptyList())
    data object Favorites : Screens("Favorite", emptyList())
}