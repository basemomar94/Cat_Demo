package com.bassem.catdemo.repo

import com.bassem.catdemo.BaseTest
import com.bassem.catdemo.data.local.CatsDao
import com.bassem.catdemo.data.repo.FavoriteRepoImp
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class FavoriteRepoTest : BaseTest() {

    private lateinit var favoriteRepo: FavoriteRepoImp
    private val daoMock: CatsDao = mockk(relaxed = true)


    override fun setup() {
        super.setup()
        favoriteRepo = FavoriteRepoImp(daoMock)
    }

    @Test
    fun test_get_favorites_list() = runTest {
        val expected = mockLocalBreedsList

        coEvery { daoMock.getAllFavorites() } returns expected

        val actual = favoriteRepo.getFavorites()

        Assertions.assertEquals(expected, actual)
        coVerify { daoMock.getAllFavorites() }
    }

    @Test
    fun test_remove_favorite_call_dao() = runTest {
        val id = "5"
        favoriteRepo.removeFavorite(id)
        coVerify { daoMock.updateFavoriteStatus(id, false) }
    }

}