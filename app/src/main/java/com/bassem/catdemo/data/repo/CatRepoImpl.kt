package com.bassem.catdemo.data.repo

import com.bassem.catdemo.data.local.CatsDao
import com.bassem.catdemo.data.models.BreedItem
import com.bassem.catdemo.data.models.Result
import com.bassem.catdemo.data.remote.CatService
import com.bassem.catdemo.utils.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatRepoImpl @Inject constructor(private val service: CatService, private val dao: CatsDao) :
    CatRepo {
    private val logger = Logger(this::class.java.simpleName)

    override suspend fun getCatsBreeds() = flow {
        emit(Result.Loading)
        logger.i("loading data..")
        val localBreeds = withContext(Dispatchers.IO) { dao.getAllBreeds() }
        try {
            val remoteBreeds = service.getCatsBreeds()
            logger.i("Successfully loaded ${remoteBreeds.size} items")
            withContext(Dispatchers.IO) { dao.insertAllBreeds(remoteBreeds) }
            emit(Result.Success(remoteBreeds))
        } catch (e: Exception) {
            if (localBreeds.isEmpty()) {
                emit(Result.Fail(e.message ?: "Unknown Error"))
                logger.e("fail to load ${e.message}")
            } else {
                logger.i("emitting local items ${localBreeds.size}")
                emit(Result.Success(localBreeds))
            }

        }
    }

    override fun List<BreedItem>.mapFavorite(localBreeds: List<BreedItem>): List<BreedItem> {
        val favoriteIds =
            localBreeds.map { it.id }.toSet()

        return this.map { breedItem ->
            breedItem.copy(isFavorite = favoriteIds.contains(breedItem.id))
        }
    }
}