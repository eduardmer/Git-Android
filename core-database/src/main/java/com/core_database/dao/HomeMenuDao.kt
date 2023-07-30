package com.core_database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.core_database.entities.HomeMenuEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeMenuDao {

    @Insert
    fun insertAll(menus: List<HomeMenuEntity>)

    @Delete
    fun delete(menu: HomeMenuEntity)

    @Query("SELECT * FROM HomeMenuEntity")
    fun getAll(): Flow<List<HomeMenuEntity>>

    @Query("SELECT * FROM HomeMenuEntity WHERE selected = :selected")
    fun getSelectedMenus(selected: Boolean): Flow<List<HomeMenuEntity>>

}