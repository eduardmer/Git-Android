package com.core_database.di

import android.content.Context
import androidx.room.Room
import com.core_database.AppDatabase
import com.core_database.dao.HomeMenuDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME).build()
    }

    @Provides
    @Singleton
    fun provideHomeMenuDao(database: AppDatabase): HomeMenuDao {
        return database.homeMenuDao()
    }

}