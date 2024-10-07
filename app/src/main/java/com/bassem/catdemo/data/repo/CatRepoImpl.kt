package com.bassem.catdemo.data.repo

import androidx.annotation.VisibleForTesting
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


        } catch (e: Exception) {
            logger.e("Unexpected error: ${e.message}")
            if (localBreeds.isEmpty()) {
                emit(Result.Fail(getExceptionMessage(e)))
            } else {
                emit(Result.Success(localBreeds))
            }


        }
    }.retry(3)

    @VisibleForTesting
    fun getExceptionMessage(e: Exception) = when (e) {
        is IOException -> "Connection Error"
        is SQLException -> "Local Parsing Error"
        is JsonParseException -> "Remote Parsing Error"
        else -> "Unexpected Error"
    }


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