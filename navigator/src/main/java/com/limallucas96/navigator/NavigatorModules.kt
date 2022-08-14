package com.limallucas96.navigator

import com.limallucas96.navigator.featureone.FeatureOneNavigatorImpl
import com.limallucas96.navigator.featureone.FeatureOneNavigator
import com.limallucas96.navigator.featuretwo.FeatureTwoNavigator
import com.limallucas96.navigator.featuretwo.FeatureTwoNavigatorImp
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
    fun providesFeatureOneNavigator() : FeatureOneNavigator {
        return FeatureOneNavigatorImpl()
    }

    @Provides
    @Singleton
    fun providesFeatureTwoNavigator() : FeatureTwoNavigator {
        return FeatureTwoNavigatorImp()
    }

}