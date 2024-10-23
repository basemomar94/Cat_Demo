package com.bassem.catdemo.di

import com.bassem.catdemo.data.remote.CatService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.thecatapi.com/v1/"

@InstallIn(SingletonComponent::class)
@Module
class NetWorkModule {
    @Singleton
    @Provides
    fun provideCatApi(): CatService {
        val log = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
        val client = OkHttpClient.Builder().addInterceptor(log).build()
        return Retrofit.Builder().baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(CatService::class.java)
    }
}