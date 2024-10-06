package com.bassem.catdemo.viewmodelstests

import com.bassem.catdemo.BaseTest
import com.bassem.catdemo.data.repo.FavoriteRepo
import com.bassem.catdemo.ui.compose.favorites.FavoriteViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteViewModelTest : BaseTest() {
    private lateinit var viewModel: FavoriteViewModel
    private val mockRepo: FavoriteRepo = mockk(relaxed = true)

    override fun setup() {
        super.setup()
        viewModel = FavoriteViewModel(mockRepo)
    }

    @Test
    fun test_remove_favorite_status() = runTest {
        val breedId = "1"
        coEvery { mockRepo.removeFavorite(breedId) } returns Unit

        viewModel.removeFavorite(breedId)
        advanceUntilIdle()

        coVerify { mockRepo.removeFavorite(breedId) }
    }
}