package com.bassem.catdemo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bassem.catdemo.data.models.BreedItem

@Dao
interface CatsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBreeds(catsList: List<BreedItem>)

    @Query("SELECT * FROM cats")
    fun getAllBreeds(): List<BreedItem>

    @Query("SELECT * FROM cats WHERE id=:breedId")
    fun getBreedById(breedId: String): BreedItem

    @Query("UPDATE cats SET isFavorite=:isFavorite WHERE id=:breedId")
    fun updateFavoriteStatus(breedId: String, isFavorite: Boolean)
}