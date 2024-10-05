package com.bassem.catdemo.data.repo

import com.bassem.catdemo.data.local.CatsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailsRepoImp @Inject constructor(private val dao: CatsDao) : DetailsRep {

    override suspend fun getBreedById(id: String) =
        withContext(Dispatchers.IO) { dao.getBreedById(id) }

    override suspend fun updateFavoriteStatus(breedId: String, isFavorite: Boolean) {
        withContext(Dispatchers.IO) { dao.updateFavoriteStatus(breedId, isFavorite) }
    }
}