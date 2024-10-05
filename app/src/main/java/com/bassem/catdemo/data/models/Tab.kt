package com.bassem.catdemo.data.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

data class Tab(val title: String, val icon: ImageVector)

val tabs = listOf(Tab("Home", Icons.Default.Home), Tab("Favorites", Icons.Default.Favorite))
