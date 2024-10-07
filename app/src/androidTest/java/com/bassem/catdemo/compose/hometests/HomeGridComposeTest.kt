package com.bassem.catdemo.compose.hometests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.bassem.catdemo.compose.BaseComposeTest
import com.bassem.catdemo.ui.compose.home.HomeGrid
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeGridComposeTest : BaseComposeTest() {


    @Test
    fun testHomeGrid_Handle_Card_Click() {
        var clickedBreedId: String? = null

        composeTestRule.setContent {
            HomeGrid(
                breeds = mockBreedsList,
                onClick = { breedId -> clickedBreedId = breedId },
                onFavoriteClick = { }
            )
        }

        mockBreedsList.forEach { breed ->
            assertTextIsDisplayed(breed.name)
        }

        clickNodeWithText(mockBreedsList[0].name)
        assert(clickedBreedId == mockBreedsList[0].id)
    }

}
