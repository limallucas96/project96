package com.limallucas96.core_data.repositories.cat

import com.limallucas96.core_common.IoDispatcher
import com.limallucas96.core_common.runSafeCall
import com.limallucas96.core_data.mappers.CatMapper.toCatEntity
import com.limallucas96.core_network.datasources.CatDataSource
import com.limallucas96.domain_model.models.Cat
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CatRepositoryImp @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val catDataSource: CatDataSource
) : CatRepository {

    override suspend fun getCats(): Result<List<Cat>> {
        return runSafeCall(ioDispatcher) {
            catDataSource.getCats().toCatEntity()
        }
    }

}