package com.example.rickandmorty.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.hilt.android.qualifiers.ApplicationContext

@Database(
    entities = [],
    version = DatabaseConstants.VERSION,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        operator fun invoke(@ApplicationContext context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { database ->
                    instance = database
                }
            }

        private fun buildDatabase(context: Context): AppDatabase = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DatabaseConstants.NAME
        ).build()
    }
}