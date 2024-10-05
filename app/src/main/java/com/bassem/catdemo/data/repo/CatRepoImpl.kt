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
            logger.e("Failed to load data: ${e.message}")
            emit(if (localBreeds.isEmpty()) Result.Fail("No internet and no local data") else Result.Success(localBreeds))
        }
    }


    override fun List<BreedItem>.mapFavorite(localBreeds: List<BreedItem>): List<BreedItem> {
        val favoriteIds = localBreeds.filter { it.isFavorite }.map { it.id }.toSet()
        logger.d("Favorite IDs: $favoriteIds")
        return this.map { breedItem ->
            val isFavorite = favoriteIds.contains(breedItem.id)
            logger.d("Mapping breed ID: ${breedItem.id}, isFavorite: $isFavorite")
            breedItem.copy(isFavorite = isFavorite)
        }
    }


    override suspend fun updateFavoriteStatus(breedId: String, isFavorite: Boolean) {
        withContext(Dispatchers.IO) { dao.updateFavoriteStatus(breedId, isFavorite) }
    }
}