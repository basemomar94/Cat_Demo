package com.bassem.di

import com.bassem.catdemo.data.remote.CatService
import com.bassem.catdemo.data.repo.CatRepo
import com.bassem.catdemo.data.repo.CatRepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun providesCatRepo(impl: CatRepoImpl): CatRepo
}