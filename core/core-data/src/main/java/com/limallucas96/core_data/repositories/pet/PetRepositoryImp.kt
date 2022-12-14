package com.limallucas96.core_data.repositories.pet

import com.limallucas96.core_common.AppDispatcherProvider
import com.limallucas96.core_common.runFlowableSafeCall
import com.limallucas96.core_common.runSuspendableSafeCall
import com.limallucas96.core_data.mappers.CatMapper.toCat
import com.limallucas96.core_database.datasource.PetDataSource
import com.limallucas96.core_database.entities.PetEntity
import com.limallucas96.domain_model.models.Cat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PetRepositoryImp @Inject constructor(
    private val dispatchers: AppDispatcherProvider,
    private val petDataSource: PetDataSource
) : PetRepository {

    override suspend fun insertPet(petName: String, petAge: Int, petPhotoUrl: String) {
        runSuspendableSafeCall(dispatchers.io) {
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