package com.limallucas96.core_common.di

import com.limallucas96.core_common.AppDispatchers
import com.limallucas96.core_common.AppDispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface DispatcherModules {

    @Singleton
    @Binds
    fun bindsAppDispatcher(appDispatchers: AppDispatchers): AppDispatcherProvider

}