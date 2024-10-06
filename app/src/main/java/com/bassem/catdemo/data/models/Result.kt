package com.bassem.catdemo.data.models


sealed class Result<T> {
    data object Loading : Result<Any?>()
    data class Success(val breedItems: List<BreedItem>) : Result<Any?>()
    data class Fail(val reasons: String) : Result<Any?>()
}
