package com.core_database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HomeMenuEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val image_id: Int,
    val description_id: Int,
    val color_id: Int,
    val selected: Boolean = false
)
