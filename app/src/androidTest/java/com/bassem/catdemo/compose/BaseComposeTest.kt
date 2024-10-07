package com.bassem.catdemo.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.bassem.catdemo.data.models.BreedItem
import org.junit.Rule

abstract class BaseComposeTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    val breed1 = BreedItem(
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

    val breed2 = BreedItem(
        dbId = 1,
        description = "new description",
        id = "26",
        name = "Aug",
        origin = "Portugal",
        temperament = "smart",
        reference_image_id = "fad",
        isFavorite = true,
        life_span = "10 - 4"
    )

    val mockBreedsList =
        listOf(
            breed1,
            breed2
        )


    fun assertTextIsDisplayed(text: String) {
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    fun assertContentDescriptionIsDisplayed(contentDescription: String) {
        composeTestRule.onNodeWithContentDescription(contentDescription).assertExists()

    }

    fun clickNodeWithContentDescription(contentDescription: String) {
        composeTestRule.onNodeWithContentDescription(contentDescription).performClick()
    }

    fun clickNodeWithText(text: String) {
        composeTestRule.onNodeWithText(text).performClick()
    }
}