package com.example.abtest.di

import com.example.abtest.Abtest
import com.example.abtest.AbtestImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface AbtestModules {

    @Singleton
    @Binds
    fun bindsAbtest(abtest: AbtestImpl): Abtest
}