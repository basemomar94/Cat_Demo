package com.bassem.catdemo.utils

import com.bassem.catdemo.data.models.BreedItem


fun String?.getImageUrl() = "https://cdn2.thecatapi.com/images/$this.jpg"

fun List<BreedItem>?.getAverageSpan(): Double {
    val firstNumbers = this?.mapNotNull { lifespan ->
        lifespan.life_span.split(" - ").firstOrNull()?.trim()?.toIntOrNull()
    }

    return if (firstNumbers?.isNotEmpty() == true) firstNumbers.average() else 0.0
}

