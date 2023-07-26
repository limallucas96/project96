package com.example.abtest.di

import com.example.abtest.Abtest
import com.example.abtest.AbtestImpl
import com.example.abtest.BuildConfig
import com.example.abtest.providers.FeatureFlagProvider
import com.example.abtest.providers.firebase.FirebaseFeatureFlagProvider
import com.example.abtest.providers.runtime.RuntimeFeatureFlagProvider
import com.example.abtest.providers.store.StoreFeatureFlagProvider
import com.example.abtest.runtime.RuntimeBehavior
import com.example.abtest.runtime.RuntimeBehaviorImpl
import com.limallucas96.core_sharedpreferences.sharedpreferences.AppSharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
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

@InstallIn(SingletonComponent::class)
@Module
object TempModules {

    @Singleton
    @Provides
    fun providesRuntimeFeatureFlag(appSharedPreferences: AppSharedPreferences): FeatureFlagProvider {
        return RuntimeFeatureFlagProvider(appSharedPreferences)
    }

    @Singleton
    @Provides
    fun providesRuntimeBehavior(
        runtimeFeatureFlagProvider: RuntimeFeatureFlagProvider,
        storeRuntimeFeatureFlagProvider: StoreFeatureFlagProvider,
        firebaseFeatureFlagProvider: FirebaseFeatureFlagProvider
    ): RuntimeBehavior {
        return RuntimeBehaviorImpl(
            runtimeFeatureFlagProvider,
            storeRuntimeFeatureFlagProvider,
            firebaseFeatureFlagProvider,
            isDebugBuild = BuildConfig.DEBUG
        )
    }

    @Singleton
    @Provides
    fun providesStoreFeatureFlagProvider(): FeatureFlagProvider {
        return StoreFeatureFlagProvider()
    }

    @Singleton
    @Provides
    fun providesFirebaseFeatureFlagProvider(): FeatureFlagProvider {
        return FirebaseFeatureFlagProvider(isDebugBuild = BuildConfig.DEBUG)
    }

}