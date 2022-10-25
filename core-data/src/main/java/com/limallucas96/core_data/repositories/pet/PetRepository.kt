package com.limallucas96.core_data.repositories.pet

import com.limallucas96.domain_model.models.Cat
import kotlinx.coroutines.flow.Flow

interface PetRepository {

    suspend fun insertPet(petName: String, petAge: Int, petPhotoUrl: String)
    suspend fun getPets(): Flow<Result<List<Cat>>>

}