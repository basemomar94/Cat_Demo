package com.bassem.catdemo

import com.bassem.catdemo.data.models.BreedItem
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

@OptIn(ExperimentalCoroutinesApi::class)
open class BaseTest {

    val mockBreedsList =
        listOf(
            BreedItem(1, "test", "23", "Sphinx", "Egypt", "loyal", "fad", false),
            BreedItem(1, "new description", "26", "Aug", "Portugal", "smart", "fad", true)
        )

    @BeforeEach
    open fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @AfterEach
    open fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

}