package com.bassem.catdemo.repo

import com.bassem.catdemo.BaseTest
import com.bassem.catdemo.data.local.CatsDao
import com.bassem.catdemo.data.repo.DetailsRepoImp
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DetailsRepoTest : BaseTest() {

    private lateinit var detailsRepoImp: DetailsRepoImp
    private val mockDao: CatsDao = mockk(relaxed = true)
    private val breedId = "26"


    override fun setup() {
        super.setup()
        detailsRepoImp = DetailsRepoImp(dao = mockDao)
    }

    @Test
    fun test_get_BreedById_returns_breed() = runTest {
        val expected = breed1
        coEvery { mockDao.getBreedById(breedId) } returns expected
        val actual = detailsRepoImp.getBreedById("26")
        Assertions.assertEquals(expected, actual)
        coVerify { mockDao.getBreedById(breedId) }
    }

    @Test
    fun test_updateFavoriteStatus_calls_dao() = runTest {
        detailsRepoImp.updateFavoriteStatus(breedId, true)
        coVerify { mockDao.updateFavoriteStatus(breedId, true) }
    }
}