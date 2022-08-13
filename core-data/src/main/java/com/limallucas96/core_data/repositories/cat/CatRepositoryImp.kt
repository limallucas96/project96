package com.limallucas96.core_data.repositories.cat

import com.limallucas96.core_common.runners.runIOSafeCall
import com.limallucas96.core_common.wrappers.ResultWrapper
import com.limallucas96.core_data.mappers.CatMapper.toCatEntity
import com.limallucas96.core_network.datasources.CatDataSource
import com.limallucas96.domain_model.Cat
import javax.inject.Inject

class CatRepositoryImp @Inject constructor(private val catDataSource: CatDataSource) :
    CatRepository {

    override suspend fun getCats(): ResultWrapper<List<Cat>> {
        return runIOSafeCall {
            catDataSource.getCats().toCatEntity()
        }
    }

}