package com.bassem.catdemo.data.models


sealed class Result {
    data object Loading : Result()
    data class Success(val breedsResult: BreedsResult) : Result()
    data class Fail(val reasons: String) : Result()
}
