package com.bassem.catdemo.data.repo

import com.bassem.catdemo.data.local.CatsDao
import com.bassem.catdemo.data.models.BreedItem
import com.bassem.catdemo.data.models.Result
import com.bassem.catdemo.data.remote.CatService
import com.bassem.catdemo.utils.Logger
import com.google.gson.JsonParseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.withContext
import java.io.IOException
import java.sql.SQLException
import javax.inject.Inject

class CatRepoImpl @Inject constructor(private val service: CatService, private val dao: CatsDao) :
    CatRepo {
    private val logger = Logger(this::class.java.simpleName)

    override suspend fun getCatsBreeds() = flow {
        emit(Result.Loading)
        logger.i("Loading data...")

        var localBreeds = withContext(Dispatchers.IO) { dao.getAllBreeds() }

        try {
            val remoteBreeds = service.getCatsBreeds().mapFavorite(localBreeds)
            logger.i("Successfully loaded remoteBreeds: ${remoteBreeds.size} items")

            withContext(Dispatchers.IO) {
                dao.deleteAllBreeds()
                dao.insertAllBreeds(remoteBreeds)
                localBreeds = dao.getAllBreeds()
            }

            emit(Result.Success(localBreeds))

        } catch (e: IOException) {
            logger.e("network error ${e.message}")
            emit(
                if (localBreeds.isEmpty()) Result.Fail(
                     "Unfortunately, no connection. no cached data"
                ) else Result.Success(localBreeds)
            )
        } catch (e: SQLException) {
            logger.e("Database error occurred. Please try again later. ${e.message}")
            emit(Result.Fail(""))
        } catch (e: JsonParseException) {
            logger.e("Parsing error: ${e.message}")
            emit(
                if (localBreeds.isEmpty()) Result.Fail(
                    "Data parsing error. Could not parse remote data."
                ) else Result.Success(localBreeds)
            )
        } catch (e: Exception) {
            logger.e("Unexpected error: ${e.message}")
            emit(
                if (localBreeds.isEmpty()) Result.Fail(
                    "An unexpected error occurred."
                ) else Result.Success(localBreeds)
            )

        }
    }.retry(3)


    override fun List<BreedItem>.mapFavorite(localBreeds: List<BreedItem>): List<BreedItem> {
        val favoriteIds = localBreeds.filter { it.isFavorite }.map { it.id }.toSet()
        return this.map { breedItem ->
            val isFavorite = favoriteIds.contains(breedItem.id)
            breedItem.copy(isFavorite = isFavorite)
        }
    }


    override suspend fun updateFavoriteStatus(breedId: String, isFavorite: Boolean) {
        withContext(Dispatchers.IO) { dao.updateFavoriteStatus(breedId, isFavorite) }
    }
}