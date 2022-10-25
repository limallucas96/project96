package com.limallucas96.core_database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.limallucas96.core_database.DatabaseConstants.DATA_BASE_VERSION
import com.limallucas96.core_database.datasource.PetDataSource
import com.limallucas96.core_database.entities.PetEntity

@Database(
    entities = [PetEntity::class],
    exportSchema = true, // Enabling schema exporting so we can test room migrations
    version = DATA_BASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPetDataSource() : PetDataSource

}