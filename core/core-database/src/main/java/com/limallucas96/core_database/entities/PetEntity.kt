package com.limallucas96.core_database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.limallucas96.core_database.DatabaseConstants.PET_ENTITY_TABLE_NAME

@Entity(tableName = PET_ENTITY_TABLE_NAME)
data class PetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val petName: String,
    val petAge: Int,
    val petProfilePictureUrl: String
)