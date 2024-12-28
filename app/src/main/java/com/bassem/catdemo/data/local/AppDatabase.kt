package com.bassem.catdemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bassem.catdemo.data.models.BreedItem

@Database(version = 1, entities = [BreedItem::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun catsDao():CatsDao
}