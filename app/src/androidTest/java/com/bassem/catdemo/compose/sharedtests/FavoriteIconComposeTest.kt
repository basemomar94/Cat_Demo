package com.bassem.catdemo.compose.sharedtests

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import com.bassem.catdemo.compose.BaseComposeTest
import com.bassem.catdemo.ui.compose.shared.FavoriteIcon
import org.junit.Test

class FavoriteIconComposeTest : BaseComposeTest() {
    private val modifier = Modifier.fillMaxWidth()

    @Test
    fun test_when_favorite_is_false_show_filled_icon() {
        composeTestRule.setContent {
            FavoriteIcon(isFavorite = true, modifier = modifier)
        }
        assertContentDescriptionIsDisplayed("Favorite icon")
    }

    @Test
    fun test_when_favorite_is_false_show_border_icon() {
        composeTestRule.setContent {
            FavoriteIcon(isFavorite = false, modifier = modifier)
        }
        assertContentDescriptionIsDisplayed("Favorite icon with border")
    }
}