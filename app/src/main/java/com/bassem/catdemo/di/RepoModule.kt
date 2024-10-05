package com.bassem.catdemo.di

import com.bassem.catdemo.data.repo.CatRepo
import com.bassem.catdemo.data.repo.CatRepoImpl
import com.bassem.catdemo.data.repo.DetailsRep
import com.bassem.catdemo.data.repo.DetailsRepoImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun providesCatRepo(impl: CatRepoImpl): CatRepo

    @Binds
    abstract fun provideDetailsRepo(impl: DetailsRepoImp): DetailsRep
}