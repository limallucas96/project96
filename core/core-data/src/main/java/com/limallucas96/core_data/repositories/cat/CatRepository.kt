package com.limallucas96.core_data.repositories.cat

import com.limallucas96.domain_model.models.Cat

interface CatRepository {

    suspend fun getCats(): Result<List<Cat>>

}