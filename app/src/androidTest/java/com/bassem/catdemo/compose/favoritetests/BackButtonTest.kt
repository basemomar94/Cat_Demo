package com.bassem.catdemo.compose.favoritetests

import com.bassem.catdemo.compose.BaseComposeTest
import com.bassem.catdemo.ui.compose.home.BackButton
import org.junit.Before
import org.junit.Test

class BackButtonTest : BaseComposeTest() {

    @Before
    fun setup() {
        composeTestRule.setContent {
            BackButton(onBackClick = {})
        }
    }

    @Test
    fun back_button_exist() {
        assertContentDescriptionIsDisplayed("Back")
    }
}