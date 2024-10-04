package com.bassem.catdemo.data.repo

import com.bassem.catdemo.data.models.BreedItem
import com.bassem.catdemo.data.models.Result
import kotlinx.coroutines.flow.Flow

interface CatRepo {
    suspend fun getCatsBreeds(): Flow<Result>
     fun List<BreedItem>.mapFavorite(localBreeds: List<BreedItem>):List<BreedItem>
}