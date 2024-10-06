package com.bassem.catdemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bassem.catdemo.data.models.BreedItem
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

@OptIn(ExperimentalCoroutinesApi::class)
open class BaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = TestResult

    val mockBreedsList =
        listOf(
            BreedItem(
                dbId = 1,
                description = "test",
                id = "23",
                name = "Sphinx",
                origin = "Egypt",
                temperament = "loyal",
                reference_image_id = "fad",
                isFavorite = false,
                life_span = "4 - 5"
            ),
            BreedItem(
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