package com.bassem.catdemo.compose.sharedtests

import com.bassem.catdemo.compose.BaseComposeTest
import com.bassem.catdemo.ui.compose.shared.ErrorTextCompose
import org.junit.Test

class ErrorTextComposeTest : BaseComposeTest() {


    @Test
    fun test_error_message_displayed_when_error_is_not_null() {
        val testError = "test_error"
        composeTestRule.setContent {
            ErrorTextCompose(message = testError)
        }
        assertTextIsDisplayed(testError)
    }

    @Test
    fun test_error_message_displayed_when_error_is_Empty() {
        val testError = ""
        composeTestRule.setContent {
            ErrorTextCompose(message = testError)
        }
        assertTextIsNotDisplayed(testError)
    }
}