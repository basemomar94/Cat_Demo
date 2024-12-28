

package com.bassem.catdemo.di

import android.content.Context
import androidx.room.Room
import com.bassem.catdemo.data.local.AppDatabase
import com.bassem.catdemo.data.local.CatsDao
import com.bassem.catdemo.utils.AppConstants.DATABASE_NAME
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
        return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .build()
    }

    @Provides
    fun provideCatsDao(appDatabase: AppDatabase): CatsDao {
        return appDatabase.catsDao()
    }

}
