package com.bassem.catdemo.data.repo

import com.bassem.catdemo.data.models.BreedItem

interface DetailsRep {

    suspend fun getBreedById(id: String): BreedItem
}