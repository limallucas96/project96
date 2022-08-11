package com.limallucas96.core_data.di

import com.limallucas96.core_data.repositories.cat.CatRepository
import com.limallucas96.core_data.repositories.cat.CatRepositoryImp
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
    abstract fun bindCatRepository(catRepositoryImp: CatRepositoryImp): CatRepository

}