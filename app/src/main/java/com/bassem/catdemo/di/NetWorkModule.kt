package com.bassem.catdemo.di

import com.bassem.catdemo.data.remote.CatService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetWorkModule {
    @Singleton
    @Provides
    fun provideCatApi(): CatService = CatService.create()
}