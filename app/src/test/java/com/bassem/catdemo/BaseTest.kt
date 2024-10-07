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
    private val breed2 = BreedItem(
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
    private val breed3 = BreedItem(
        dbId = 4,
        description = "new description",
        id = "26",
        name = "Shiro",
        origin = "Germany",
        temperament = "smart",
        reference_image_id = "fad",
        isFavorite = true,
        life_span = "10 - 4"
    )
    private val breed4 = BreedItem(
        dbId = 7,
        description = "new good",
        id = "26",
        name = "Z",
        origin = "Zagreb",
        temperament = "smart",
        reference_image_id = "fad",
        isFavorite = true,
        life_span = "10 - 4"
    )
    val mockLocalBreedsList = listOf(breed1, breed2)

    val mockRemoteList = listOf(breed1, breed2, breed3, breed4)

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