package com.limallucas96.core_data.repositories.cat

import com.limallucas96.core_data.datasource.CatDataSource
import javax.inject.Inject

class CatRepositoryImp @Inject constructor(private val catDataSource: CatDataSource) :
    CatRepository {

    override suspend fun getCats() {
        catDataSource.getCats()
    }

}