package com.bassem.catdemo.data.remote

import com.bassem.catdemo.data.models.BreedItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CatService {
    @GET("breeds?limit=50&page=0")
    suspend fun getCatsBreeds(): List<BreedItem>

    @GET("breeds")
    suspend fun getCatsBreeds(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): List<BreedItem>

    companion object {
        private const val BASE_URL = "https://api.thecatapi.com/v1/"

        fun create(): CatService {
            val log = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
            val client = OkHttpClient.Builder().addInterceptor(log).build()
            return Retrofit.Builder().baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(CatService::class.java)
        }
    }
}