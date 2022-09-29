package com.limallucas96.core_data.repositories

import com.limallucas96.domain_model.models.Cat
import kotlinx.coroutines.flow.Flow

interface PetRepository {

    suspend fun insertCat(cat: Cat)
    suspend fun getCat(): Flow<Result<List<Cat>>>

}