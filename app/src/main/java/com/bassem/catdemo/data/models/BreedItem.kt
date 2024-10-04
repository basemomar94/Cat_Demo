package com.bassem.catdemo.data.models

data class BreedItem(
    val description: String,
    val id: String,
    val name: String,
    val origin: String,
    val temperament: String,
    val reference_image_id: String,
    var isFavorite: Boolean = false
)