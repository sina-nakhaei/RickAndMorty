package com.example.rickandmorty.core.module

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.core.database.AppDatabase
import com.example.rickandmorty.core.database.DatabaseConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {
    @Provides
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): AppDatabase {
        return Room
            .databaseBuilder(
                appContext,
                AppDatabase::class.java,
                DatabaseConstants.NAME
            )
            .build()
    }
}