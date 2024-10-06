package com.bassem.catdemo.data.repo

import com.bassem.catdemo.data.local.CatsDao
import com.bassem.catdemo.data.models.BreedItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteRepoImp @Inject constructor(private val dao: CatsDao) : FavoriteRepo {

    override suspend fun getFavorites(): List<BreedItem> =
        withContext(Dispatchers.IO) { dao.getAllFavorites() }

    override suspend fun removeFavorite(id: String) {
        withContext(Dispatchers.IO) { dao.updateFavoriteStatus(breedId = id, false) }
    }

}