package com.bassem.catdemo.data.repo

import com.bassem.catdemo.data.models.Result
import com.bassem.catdemo.data.remote.CatService
import com.bassem.catdemo.utils.Logger
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatRepoImpl @Inject constructor(private val service: CatService) : CatRepo {
    private val logger = Logger(this::class.java.simpleName)

    override fun getCatsBreeds() = flow {
        emit(Result.Loading)
        logger.i("loading data..")
        try {
            val result = service.getCatsBreeds()
            emit(Result.Success(result))
            logger.i("Successfully loaded ${result.size} items")
        } catch (e: Exception) {
            emit(Result.Fail(e.message ?: "Unknown Error"))
            logger.e("fail to load ${e.message}")
        }
    }
}