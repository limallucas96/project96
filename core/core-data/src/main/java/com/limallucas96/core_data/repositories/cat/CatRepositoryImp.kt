package com.limallucas96.core_data.repositories.cat

import com.limallucas96.core_common.AppDispatcherProvider
import com.limallucas96.core_common.AppDispatchers
import com.limallucas96.core_common.runSafeCall
import com.limallucas96.core_data.mappers.CatMapper.toCatEntity
import com.limallucas96.core_network.datasources.CatDataSource
import com.limallucas96.domain_model.models.Cat
import javax.inject.Inject

class CatRepositoryImp @Inject constructor(
    private val dispatcher: AppDispatcherProvider,
    private val catDataSource: CatDataSource
) : CatRepository {

    override suspend fun getCats(): Result<List<Cat>> {
        return runSafeCall(dispatcher.io) {
            catDataSource.getCats().toCatEntity()
        }
    }
}