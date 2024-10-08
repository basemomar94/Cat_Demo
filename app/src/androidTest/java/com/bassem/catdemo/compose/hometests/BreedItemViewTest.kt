package com.bassem.catdemo.compose.hometests

import com.bassem.catdemo.compose.BaseComposeTest
import com.bassem.catdemo.ui.compose.home.BreedViewItemCompose
import com.bassem.catdemo.utils.getImageUrl
import org.junit.Before
import org.junit.Test

class BreedItemViewTest : BaseComposeTest() {
    private var isFavoriteClicked = false

    @Before
    fun setUp() {
        composeTestRule.setContent {
            BreedViewItemCompose(
                name = breed1.name,
                imageUrl = breed2.reference_image_id.getImageUrl(),
                isFavorite = breed1.isFavorite,
                onCardClick = { },
                onFavoriteClick = { isFavoriteClicked = true }
            )
        }
    }

    @Test
    fun test_item_display_name() {
        assertTextIsDisplayed(breed1.name)
    }

    @Test
    fun test_item_display_favorite_icon() {
        assertContentDescriptionIsDisplayed("Favorite icon with border")
    }

    @Test
    fun test_favorite_icon_action() {
        clickNodeWithContentDescription("Favorite icon with border")
        assert(isFavoriteClicked)
    }
}