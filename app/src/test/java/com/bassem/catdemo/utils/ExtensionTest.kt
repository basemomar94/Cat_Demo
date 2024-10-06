package com.bassem.catdemo.utils

import com.bassem.catdemo.BaseTest
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ExtensionTest : BaseTest() {


    @Test
    fun test_get_image_url() = runTest {
        val actual = "test".getImageUrl()
        val expected = "https://cdn2.thecatapi.com/images/test.jpg"
        Assertions.assertEquals(expected, actual)
    }
}