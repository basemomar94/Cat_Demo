

package com.bassem.catdemo.di

import android.content.Context
import com.bassem.catdemo.data.local.AppDatabase
import com.bassem.catdemo.data.local.CatsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideCatsDao(appDatabase: AppDatabase): CatsDao {
        return appDatabase.catsDao()
    }

}
