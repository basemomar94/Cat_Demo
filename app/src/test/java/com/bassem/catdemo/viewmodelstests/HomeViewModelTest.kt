package com.bassem.catdemo.viewmodelstests

import com.bassem.catdemo.BaseTest
import com.bassem.catdemo.data.models.Result
import com.bassem.catdemo.data.repo.CatRepo
import com.bassem.catdemo.ui.compose.home.HomeViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

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

        coVerify { mockRepo.getCatsBreeds() }

        val emittedResult = viewModel.breedsList.first()

        assert(emittedResult == loadingState)
    }


    @Test
    fun fetchBreedsList_updates_breedsList_with_fetched_results() = runTest {

        val mockBreedsList = mockBreedsList

        val mockResult = Result.Success(mockBreedsList)

        coEvery { mockRepo.getCatsBreeds() } returns flowOf(mockResult)

        viewModel.fetchBreedsList()

        coVerify { mockRepo.getCatsBreeds() }

        val emittedResult = viewModel.breedsList.first()

        assert(emittedResult == mockResult)
    }


}







