package com.limallucas96.core_data.repositories.pet

import com.limallucas96.core_common.AppDispatchers
import com.limallucas96.core_common.runFlowableSafeCall
import com.limallucas96.core_common.runSafeCall
import com.limallucas96.core_data.mappers.CatMapper.toCat
import com.limallucas96.core_database.datasource.PetDataSource
import com.limallucas96.core_database.entities.PetEntity
import com.limallucas96.domain_model.models.Cat
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PetRepositoryImp @Inject constructor(
    private val dispatchers: AppDispatchers,
    private val petDataSource: PetDataSource
) : PetRepository {

    override suspend fun insertPet(petName: String, petAge: Int, petPhotoUrl: String) {
        runSafeCall(dispatchers.io) {
            petDataSource.insertPet(
                PetEntity(
                    petName = petName,
                    petAge = petAge,
                    petProfilePictureUrl = petPhotoUrl
                )
            )
        }
    }

    override suspend fun getPets(): Flow<Result<List<Cat>>> {
        return runFlowableSafeCall(dispatchers.io) {
            petDataSource.getPets().map { it.toCat() }
        }
    }

}