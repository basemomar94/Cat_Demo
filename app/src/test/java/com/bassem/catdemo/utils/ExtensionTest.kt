package com.bassem.catdemo.utils

import com.bassem.catdemo.BaseTest
import com.bassem.catdemo.data.models.BreedItem
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
    fun test_average_life_span_when_list_is_not_empty() {
        val actual = mockLocalBreedsList.getAverageSpan()
        val expected = 7.0
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun test_average_life_span_when_list_is_empty() {
        val actual = emptyList<BreedItem>().getAverageSpan()
        val expected = 0.0
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun test_average_life_span_when_list_is_null() {
        val actual = null.getAverageSpan()
        val expected = 0.0
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun test_get_temperament_list_from_string() {
        val mockString = "Loyal , Social , Loveable"
        val actual = mockString.getListOfTemperament().size
        Assertions.assertEquals(3, actual)
    }

    @Test
    fun test_get_temperament__list_from_string_when_string_is_null() {
        val actual = null.getListOfTemperament().size
        Assertions.assertEquals(0, actual)
    }
}