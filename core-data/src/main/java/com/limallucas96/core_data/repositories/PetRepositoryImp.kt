package com.limallucas96.core_data.repositories

import com.limallucas96.core_common.AppDispatchers
import com.limallucas96.core_common.runFlowableSafeCall
import com.limallucas96.core_common.runSafeCall
import com.limallucas96.core_data.mappers.CatMapper.toCat
import com.limallucas96.core_data.mappers.CatMapper.toPetEntity
import com.limallucas96.core_database.datasource.PetDataSource
import com.limallucas96.core_database.entities.PetEntity
import com.limallucas96.domain_model.models.Cat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PetRepositoryImp @Inject constructor(
    private val dispatchers: AppDispatchers,
    private val petDataSource: PetDataSource
) : PetRepository {

    override suspend fun insertCat(cat: Cat) {
        runSafeCall(dispatchers.io) {
            petDataSource.insertPet(cat.toPetEntity())
        }
    }

    override suspend fun getCat(): Flow<Result<List<Cat>>> {
        return flowOf()
    }

}