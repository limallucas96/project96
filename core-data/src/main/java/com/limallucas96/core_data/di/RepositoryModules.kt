package com.limallucas96.core_data.di

import com.limallucas96.core_data.repositories.PetRepository
import com.limallucas96.core_data.repositories.PetRepositoryImp
import com.limallucas96.core_data.repositories.cat.CatRepository
import com.limallucas96.core_data.repositories.cat.CatRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModules {

    @Singleton
    @Binds
    fun bindsCatRepository(catRepositoryImp: CatRepositoryImp): CatRepository

    @Singleton
    @Binds
    fun bindsPetRepository(petRepositoryImp: PetRepositoryImp): PetRepository

}