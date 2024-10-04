package com.bassem.catdemo.data.models

data class BreedItem(
    val description: String,
    val id: String,
    val image: Image,
    val name: String,
    val origin: String,
    val temperament: String,
)