package com.limallucas96.core_database.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.limallucas96.core_database.DatabaseConstants.PET_ENTITY_TABLE_NAME
import com.limallucas96.core_database.entities.PetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PetDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(petEntity: PetEntity)

    @Query("SELECT * FROM $PET_ENTITY_TABLE_NAME")
    suspend fun getPets(): Flow<List<PetEntity>>

}