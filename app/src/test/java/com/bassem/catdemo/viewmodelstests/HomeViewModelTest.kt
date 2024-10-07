package com.bassem.catdemo.viewmodelstests

import com.bassem.catdemo.BaseTest
import com.bassem.catdemo.data.models.Result
import com.bassem.catdemo.data.repo.CatRepo
import com.bassem.catdemo.ui.compose.home.HomeViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest : BaseTest() {

    private lateinit var viewModel: HomeViewModel
    private val mockRepo: CatRepo = mockk(relaxed = true)


    override fun setup() {
        super.setup()
        viewModel = HomeViewModel(mockRepo)
    }

    @Test
    fun fetchBreedsList_emits_loading_state_before_fetching_data() = runTest {
        val loadingState = Result.Loading

        coEvery { mockRepo.getCatsBreeds() } returns flowOf(loadingState)

        viewModel.fetchBreedsList()

        advanceUntilIdle()

        coVerify { mockRepo.getCatsBreeds() }

        val emittedResult = viewModel.breedsList.first()

        assert(emittedResult == loadingState)
    }


    @Test
    fun fetchBreedsList_updates_breedsList_with_fetched_results() = runTest {

        val mockBreedsList = mockBreedsList

        val exceptedResult = Result.Success(mockBreedsList)

        coEvery { mockRepo.getCatsBreeds() } returns flowOf(exceptedResult)

        viewModel.fetchBreedsList()

        advanceUntilIdle()

        coVerify { mockRepo.getCatsBreeds() }

        val emittedResult = viewModel.breedsList.first()

        assert(emittedResult == exceptedResult)
    }

    @Test
    fun fetchBreedsList_fail_case() = runTest {

        val exceptedResult = Result.Fail("Fail")

        coEvery { mockRepo.getCatsBreeds() } returns flowOf(exceptedResult)

        viewModel.fetchBreedsList()

        advanceUntilIdle()

        coVerify { mockRepo.getCatsBreeds() }

        val actual  = viewModel.breedsList.first()

        assert(actual == exceptedResult)
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







