package com.bassem.catdemo.compose.sharedtests

import com.bassem.catdemo.compose.BaseComposeTest
import com.bassem.catdemo.ui.compose.shared.SearchBar
import org.junit.Before
import org.junit.Test

class SearchBarTest : BaseComposeTest() {
    private var searchQuery = "test"

    @Before
    fun setup() {
        composeTestRule.setContent {
            SearchBar(query = searchQuery) { newQuery ->
                searchQuery = newQuery // Update searchQuery here
            }
        }
    }

    @Test
    fun test_search_icon_displayed() {
        assertContentDescriptionIsDisplayed("Search Icon")
    }

    @Test
    fun test_search_bar_is_displayed_with_searchQuery() {
        assertTextIsDisplayed(searchQuery)
    }

    @Test
    fun test_clear_icon_is_displayed_when_query_not_empty() {
        assertContentDescriptionIsDisplayed("Clear")
    }
}
