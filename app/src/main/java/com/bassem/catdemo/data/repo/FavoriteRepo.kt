package com.bassem.catdemo.data.repo

import com.bassem.catdemo.data.models.BreedItem

interface FavoriteRepo {

    suspend fun getFavorites(): List<BreedItem>

    suspend fun removeFavorite(id: String)
}