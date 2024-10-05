package com.bassem.catdemo.data.repo

import com.bassem.catdemo.data.models.BreedItem

interface DetailsRep {

    suspend fun getBreedById(id: Int): BreedItem
    suspend fun updateFavoriteStatus(breedId: String, isFavorite: Boolean)
}