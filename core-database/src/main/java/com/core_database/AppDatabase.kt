package com.core_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.core_database.AppDatabase.Companion.DB_VERSION
import com.core_database.dao.HomeMenuDao
import com.core_database.entities.HomeMenuEntity

@Database(entities = [HomeMenuEntity::class], version = DB_VERSION)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "git_database"
        const val DB_VERSION = 1
    }

    abstract fun homeMenuDao(): HomeMenuDao

}