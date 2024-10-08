package com.bassem.catdemo.utils

import com.bassem.catdemo.data.models.BreedItem

object AppConstants {

    const val DATABASE_NAME = "cats_database"
    const val BREED_ID = "breedId"

    private val breed1 = BreedItem(
        dbId = 1,
        description = "test",
        id = "23",
        name = "Sphinx",
        origin = "Egypt",
        temperament = "loyal",
        reference_image_id = "fad",
        isFavorite = false,
        life_span = "4 - 5"
    )
    private val breed2 = BreedItem(
        dbId = 1,
        description = "new description",
        id = "2",
        name = "Aug",
        origin = "Portugal",
        temperament = "smart",
        reference_image_id = "fad",
        isFavorite = true,
        life_span = "10 - 4"
    )
    private val breed3 = BreedItem(
        dbId = 4,
        description = "new description",
        id = "21",
        name = "Shiro",
        origin = "Germany",
        temperament = "smart",
        reference_image_id = "fad",
        isFavorite = true,
        life_span = "10 - 4"
    )
    private val breed4 = BreedItem(
        dbId = 7,
        description = "new good",
        id = "26",
        name = "Z",
        origin = "Zagreb",
        temperament = "smart",
        reference_image_id = "fad",
        isFavorite = true,
        life_span = "10 - 4"
    )
    val mockRemoteList = listOf(breed1, breed2, breed3, breed4)

}