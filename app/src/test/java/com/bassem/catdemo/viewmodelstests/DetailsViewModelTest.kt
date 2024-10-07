package com.bassem.catdemo.viewmodelstests

import androidx.lifecycle.SavedStateHandle
import com.bassem.catdemo.BaseTest
import com.bassem.catdemo.data.repo.DetailsRep
import com.bassem.catdemo.ui.compose.details.DetailsViewModel
import com.bassem.catdemo.utils.AppConstants.BREED_ID
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailsViewModelTest : BaseTest() {

    private lateinit var viewModel: DetailsViewModel
    private val mockRepo: DetailsRep = mockk(relaxed = true)
    private lateinit var savedStateHandle: SavedStateHandle


    override fun setup() {
        super.setup()
        savedStateHandle = SavedStateHandle(mapOf(BREED_ID to "1"))
        viewModel = DetailsViewModel(detailsRepo = mockRepo, savedStateHandle = savedStateHandle)
    }


    @Test
    fun test_et_breed_by_id() = runTest {
        val expectedBreed = mockLocalBreedsList.first()
        coEvery { mockRepo.getBreedById("1") } returns expectedBreed

        viewModel.getBreedById()

        val breed = viewModel.breed.first()

        assert(breed == expectedBreed)

    }

    @Test
    fun test_update_favorite_status() = runTest {
        val breedId = "1"
        val isFavorite = true
        coEvery { mockRepo.updateFavoriteStatus(breedId, isFavorite) } returns Unit

        viewModel.updateFavoriteStatus(breedId, isFavorite)
        advanceUntilIdle()

        coVerify { mockRepo.updateFavoriteStatus(breedId, isFavorite) }
    }
}