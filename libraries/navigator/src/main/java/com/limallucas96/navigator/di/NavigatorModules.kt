package com.limallucas96.navigator.di

import com.limallucas96.navigator.featurehome.FeatureHomeNavigator
import com.limallucas96.navigator.featurehome.FeatureHomeNavigatorImpl
import com.limallucas96.navigator.featureone.FeatureOneNavigator
import com.limallucas96.navigator.featureone.FeatureOneNavigatorImpl
import com.limallucas96.navigator.featuretwo.FeatureTwoNavigator
import com.limallucas96.navigator.featuretwo.FeatureTwoNavigatorImpl
import com.limallucas96.navigator.fragment.FragmentNavigator
import com.limallucas96.navigator.fragment.FragmentNavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NavigatorModules {

    @Provides
    @Singleton
    fun providesFeatureOneNavigator(): FeatureOneNavigator {
        return FeatureOneNavigatorImpl()
    }

    @Provides
    @Singleton
    fun providesFeatureTwoNavigator(): FeatureTwoNavigator {
        return FeatureTwoNavigatorImpl()
    }

    @Provides
    @Singleton
    fun providesFeatureHomeNavigator(): FeatureHomeNavigator {
        return FeatureHomeNavigatorImpl()
    }

    @Provides
    @Singleton
    fun providesFragmentNavigator(): FragmentNavigator {
        return FragmentNavigatorImpl()
    }

}