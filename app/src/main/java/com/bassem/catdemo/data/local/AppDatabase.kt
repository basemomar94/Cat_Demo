package com.bassem.catdemo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bassem.catdemo.data.models.BreedItem
import com.bassem.catdemo.utils.AppConstants.DATABASE_NAME

@Database(version = 1, entities = [BreedItem::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun catsDao():CatsDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}