package com.bassem.catdemo.compose.detailstests

import com.bassem.catdemo.compose.BaseComposeTest
import com.bassem.catdemo.ui.compose.details.DetailsCompose
import com.bassem.catdemo.utils.getImageUrl
import com.bassem.catdemo.utils.getListOfTemperament
import org.junit.Before
import org.junit.Test

class DetailsComposeTest : BaseComposeTest() {


    @Before
    fun setup() {
        composeTestRule.setContent {
            DetailsCompose(
                name = breed1.name,
                imageUrl = breed1.reference_image_id.getImageUrl(),
                description = breed1.description,
                origin = breed1.origin,
                temperament = breed1.temperament,
                isFavorite = breed1.isFavorite
            ) {

            }
        }
    }

    @Test
    fun test_breed_name_is_displayed() {
        assertTextIsDisplayed(breed1.name)
    }

    @Test
    fun test_breed_description_is_displayed() {
        assertTextIsDisplayed(breed1.description)
    }

    @Test
    fun test_breed_origin_is_displayed() {
        assertTextIsDisplayed("Origin: ${breed1.origin}")
    }

    @Test
    fun test_breed_temperament_is_displayed() {
        breed1.temperament.getListOfTemperament().forEach {
            assertTextIsDisplayed(it)
        }
    }

    @Test
    fun test_favorite_icon_is_displayed() {
        assertContentDescriptionIsDisplayed("Favorite icon with border")
    }

}