package com.bassem.catdemo

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.bassem.catdemo.data.local.AppDatabase
import com.bassem.catdemo.data.local.CatsDao
import com.bassem.catdemo.data.models.BreedItem
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DaoTest {
    private lateinit var db: AppDatabase
    private lateinit var appDao: CatsDao
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val mockBreedsList =
        listOf(
            BreedItem(1, "test", "23", "Sphinx", "Egypt", "loyal", "fad", false),
            BreedItem(2, "new description", "26", "Aug", "Portugal", "smart", "fad", true)
        )

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries()
            .build()
        appDao = db.catsDao()
        appDao.insertAllBreeds(mockBreedsList)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun test_inserting_cat_breeds() = runTest {
        val actual = appDao.getAllBreeds()
        Assert.assertEquals(2, actual.size)
    }

    @Test
    fun test_delete_all_breeds() = runTest {
        appDao.deleteAllBreeds()
        val actual = appDao.getAllBreeds().size
        Assert.assertEquals(0, actual)
    }

    @Test
    fun test_get_breed_by_id() = runTest {
        val actual = appDao.getBreedById("23")
        val expected = mockBreedsList.first()
        Assert.assertEquals(expected, actual)

    }

    @Test
    fun test_update_favorite_status_true() = runTest {
        appDao.updateFavoriteStatus("23", true)
        val actual = appDao.getBreedById("23").isFavorite
        Assert.assertTrue(actual)

    }

    @Test
    fun test_update_favorite_status_false() = runTest {
        appDao.updateFavoriteStatus("26", false)
        val actual = appDao.getBreedById("26").isFavorite
        Assert.assertFalse(actual)

    }

    @Test
    fun test_get_favorites_breeds() = runTest {
        val actual = appDao.getAllFavorites().size
        Assert.assertEquals(1, actual)
    }

}