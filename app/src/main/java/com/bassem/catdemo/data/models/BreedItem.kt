package com.bassem.catdemo.data.models

import kotlinx.serialization.Serializable

@Serializable
data class BreedItem(
    val description: String = "",
    val id: String = "",
    val name: String = "",
    val origin: String = "",
    val temperament: String = "",
    val reference_image_id: String = "",
    var isFavorite: Boolean = false
)