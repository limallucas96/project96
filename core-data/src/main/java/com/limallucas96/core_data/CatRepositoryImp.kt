package com.limallucas96.core_data

import javax.inject.Inject

class CatRepositoryImp @Inject constructor(private val catDataSource: CatDataSource) : CatRepository {

    override suspend fun getCats() {
        catDataSource.getCats()
    }

}