package com.bassem.catdemo.viewmodelstests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bassem.catdemo.BaseTest
import com.bassem.catdemo.data.models.Result
import com.bassem.catdemo.data.repo.CatRepo
import com.bassem.catdemo.ui.compose.home.HomeViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.Test

class HomeViewModelTest : BaseTest() {

    private lateinit var viewModel: HomeViewModel
    private val mockRepo: CatRepo = mockk()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = TestResult
    override fun setup() {
        super.setup()
        viewModel = HomeViewModel(mockRepo)
    }

    @Test
    fun `fetchBreedsList emits loading state before fetching data`(): Unit = runTest {
        val mockResult = Result.Loading

        coEvery { mockRepo.getCatsBreeds() } returns flowOf(mockResult)

        viewModel.fetchBreedsList()

        coVerify { mockRepo.getCatsBreeds() }

        val emittedResult = viewModel.breedsList.first()

        assert(emittedResult == mockResult)
    }

    @Test
    fun `fetchBreedsList emits fail state in case exception`() = runTest {
        val mockResult = Result.Fail("mock")

        coEvery { mockRepo.getCatsBreeds() } returns flow {
            emit(Result.Loading)
            throw Exception("mock")
        }

        viewModel.fetchBreedsList()

        coVerify { mockRepo.getCatsBreeds() }

        val emittedResult = viewModel.breedsList.first()

        assert(emittedResult == mockResult)
    }

    @Test
    fun `fetchBreedsList updates breedsList with fetched results`() = runTest {
        val mockBreedsList = mockBreedsList
        val mockResult = Result.Success(mockBreedsList)

        coEvery { mockRepo.getCatsBreeds() } returns flowOf(mockResult)

        viewModel.fetchBreedsList()

        coVerify { mockRepo.getCatsBreeds() }

        val emittedResult = viewModel.breedsList.first()

        assert(emittedResult == mockResult)
    }


}







