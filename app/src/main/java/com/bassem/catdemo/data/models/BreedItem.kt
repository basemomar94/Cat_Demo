package com.bassem.catdemo.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("cats")
data class BreedItem(
    @PrimaryKey(autoGenerate = true)
    var dbId: Int,
    val description: String = "",
    val id: String = "",
    val name: String = "",
    val origin: String = "",
    val temperament: String = "",
    val reference_image_id: String ?= "",
    var isFavorite: Boolean = false,
    val life_span: String = ""
)