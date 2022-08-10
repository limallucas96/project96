package com.limallucas96.core_data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModules {

    @Singleton
    @Binds
    abstract fun bindCatRepository(catRepositoryImp: CatRepositoryImp) : CatRepository

}