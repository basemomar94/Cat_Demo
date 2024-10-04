package com.bassem.catdemo.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bassem.catdemo.data.models.BreedItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CatsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBreeds(catsList: List<BreedItem>)

    @Query("SELECT * FROM cats")
    fun getAllBreeds(): Flow<List<BreedItem>>

    @Query("SELECT * FROM cats WHERE id=:breedId")
    fun getCatById(breedId: String): Flow<BreedItem>
}