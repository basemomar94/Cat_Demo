package com.bassem.catdemo.data.repo

import com.bassem.catdemo.data.models.Result
import kotlinx.coroutines.flow.Flow

interface CatRepo {
    fun getCatsBreeds(): Flow<Result>
}