package com.bassem.catdemo.utils

import com.bassem.catdemo.BaseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ExtensionTest : BaseTest() {


    @Test
    fun test_get_image_url() {
        val actual = "test".getImageUrl()
        val expected = "https://cdn2.thecatapi.com/images/test.jpg"
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun test_average_life_span() {
        val actual = mockLocalBreedsList.getAverageSpan()
        val expected = 7.0
        Assertions.assertEquals(expected, actual)

    }

    @Test
    fun test_get_list_from_string() {
        val mockString = "Loyal , Social , Loveable"
        val actual = mockString.getListOfTemperament().size
        Assertions.assertEquals(3, actual)
    }
}