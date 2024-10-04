package com.bassem.catdemo.ui.compose

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens {
    @Serializable
    data object Home : Screens()

    @Serializable
    data class Details(val breedItemId: String) : Screens()

    @Serializable
    data object Favorites : Screens()
}
