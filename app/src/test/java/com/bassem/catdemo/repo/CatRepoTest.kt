package com.bassem.catdemo.repo

import com.bassem.catdemo.BaseTest
import com.bassem.catdemo.data.local.CatsDao
import com.bassem.catdemo.data.models.Result
import com.bassem.catdemo.data.remote.CatService
import com.bassem.catdemo.data.repo.CatRepoImpl
import com.google.gson.JsonParseException
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.IOException
import java.sql.SQLException
import java.util.stream.Stream

class CatRepoTest : BaseTest() {
    private lateinit var catRepo: CatRepoImpl
    private val mockDao: CatsDao = mockk(relaxed = true)
    private val mockServices: CatService = mockk(relaxed = true)

    override fun setup() {
        super.setup()
        catRepo = CatRepoImpl(mockServices, mockDao)
    }

    @Test
    fun test_getCatBreeds_emits_Loading() = runTest {

        coEvery { mockDao.getAllBreeds() } returns mockLocalBreedsList
        coEvery { mockServices.getCatsBreeds() } returns mockRemoteList
        coEvery { mockDao.deleteAllBreeds() } just Runs
        coEvery { mockDao.insertAllBreeds(mockRemoteList) } just Runs

        val actualList = catRepo.getCatsBreeds().toList()

        Assertions.assertTrue(actualList[0] is Result.Loading)
        Assertions.assertTrue(actualList[1] is Result.Success)
        Assertions.assertTrue((actualList[1] as Result.Success).breedItems == mockLocalBreedsList)
    }

    @Test
    fun test_handle_net_work_error_while_there_is_cached_list() = runTest {
        coEvery { mockDao.getAllBreeds() } returns mockLocalBreedsList
        coEvery { mockServices.getCatsBreeds() } throws IOException("NetWork error")

        val actualList = catRepo.getCatsBreeds().toList()

        Assertions.assertTrue(actualList[0] is Result.Loading)
        Assertions.assertTrue(actualList[1] is Result.Success)
        Assertions.assertTrue((actualList[1] as Result.Success).breedItems == mockLocalBreedsList)
    }

    @Test
    fun test_handle_net_work_error_while_there_no_cached_list() = runTest {
        coEvery { mockDao.getAllBreeds() } returns emptyList()
        coEvery { mockServices.getCatsBreeds() } throws IOException("NetWork error")

        val actualList = catRepo.getCatsBreeds().toList()

        Assertions.assertTrue(actualList[0] is Result.Loading)
        Assertions.assertTrue(actualList[1] is Result.Fail)
        Assertions.assertTrue((actualList[1] as Result.Fail).reasons == "Connection Error")
    }


    @Test
    fun test_handle_remote_parsing_error_no_cache() = runTest {
        coEvery { mockDao.getAllBreeds() } returns emptyList()
        coEvery { mockServices.getCatsBreeds() } throws JsonParseException("Parsing error")

        val actualList = catRepo.getCatsBreeds().toList()

        Assertions.assertTrue(actualList[0] is Result.Loading)
        Assertions.assertTrue(actualList[1] is Result.Fail)
        Assertions.assertTrue((actualList[1] as Result.Fail).reasons == "Remote Parsing Error")
    }

    @Test
    fun test_handle_remote_parsing_error_with_cache() = runTest {
        coEvery { mockDao.getAllBreeds() } returns mockLocalBreedsList
        coEvery { mockServices.getCatsBreeds() } throws JsonParseException("Parsing error")

        val actualList = catRepo.getCatsBreeds().toList()

        Assertions.assertTrue(actualList[0] is Result.Loading)
        Assertions.assertTrue(actualList[1] is Result.Success)
        Assertions.assertTrue((actualList[1] as Result.Success).breedItems == mockLocalBreedsList)
    }

    @Test
    fun test_handle_local_parsing_error() = runTest {
        coEvery { mockDao.getAllBreeds() } returns emptyList()
        coEvery { mockServices.getCatsBreeds() } throws SQLException("Parsing error")

        val actualList = catRepo.getCatsBreeds().toList()

        Assertions.assertTrue(actualList[0] is Result.Loading)
        Assertions.assertTrue(actualList[1] is Result.Fail)
        Assertions.assertTrue((actualList[1] as Result.Fail).reasons == "Local Parsing Error")
    }

    @Test
    fun test_handle_remote_parsing_local_with_cache() = runTest {
        coEvery { mockDao.getAllBreeds() } returns mockLocalBreedsList
        coEvery { mockServices.getCatsBreeds() } throws SQLException("Parsing error")

        val actualList = catRepo.getCatsBreeds().toList()

        Assertions.assertTrue(actualList[0] is Result.Loading)
        Assertions.assertTrue(actualList[1] is Result.Success)
        Assertions.assertTrue((actualList[1] as Result.Success).breedItems == mockLocalBreedsList)
    }

    @Test
    fun test_handle_unexpected_error_no_cache() = runTest {
        coEvery { mockDao.getAllBreeds() } returns emptyList()
        coEvery { mockServices.getCatsBreeds() } throws Exception("Unexpected Error")

        val actualList = catRepo.getCatsBreeds().toList()

        Assertions.assertTrue(actualList[0] is Result.Loading)
        Assertions.assertTrue(actualList[1] is Result.Fail)
        Assertions.assertTrue((actualList[1] as Result.Fail).reasons == "Unexpected Error")
    }

    @Test
    fun test_handle_unexpected_error_with_cache() = runTest {
        coEvery { mockDao.getAllBreeds() } returns mockLocalBreedsList
        coEvery { mockServices.getCatsBreeds() } throws Exception("Unexpected error")

        val actualList = catRepo.getCatsBreeds().toList()

        Assertions.assertTrue(actualList[0] is Result.Loading)
        Assertions.assertTrue(actualList[1] is Result.Success)
        Assertions.assertTrue((actualList[1] as Result.Success).breedItems == mockLocalBreedsList)
    }

    @Test
    fun test_update_favorite_call_dao() = runTest {
        val id = "5"
        catRepo.updateFavoriteStatus(id,true)
        coVerify { mockDao.updateFavoriteStatus(id, true) }
    }


    @MethodSource("provideException")
    @ParameterizedTest
    fun test_get_exception_message_return_correct_message(
        exception: Exception,
        message: String
    ) {
        val actual = catRepo.getExceptionMessage(exception)
        Assertions.assertEquals(message, actual)

    }

    companion object {
        @JvmStatic
        fun provideException(): Stream<Arguments> = Stream.of(
            Arguments.of(IOException(), "Connection Error"),
            Arguments.of(SQLException(), "Local Parsing Error"),
            Arguments.of(JsonParseException(""), "Remote Parsing Error"),
            Arguments.of(Exception(), "Unexpected Error")
        )
    }
}