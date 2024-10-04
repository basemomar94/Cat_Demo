package com.bassem.catdemo.data.remote

import com.bassem.catdemo.data.models.BreedItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CatService {
    @GET("breeds?limit=10&page=0")
    suspend fun getCatsBreeds(): List<BreedItem>

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