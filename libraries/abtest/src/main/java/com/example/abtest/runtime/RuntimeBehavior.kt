package com.example.abtest.runtime

import com.example.abtest.providers.FeatureFlagProvider
import com.example.abtest.featureflags.Feature
import java.util.concurrent.CopyOnWriteArrayList

interface RuntimeBehavior {
    val providers: CopyOnWriteArrayList<FeatureFlagProvider>
    fun isFeatureEnabled(feature: Feature): Boolean
    fun addProvider(provider: FeatureFlagProvider)
    fun initialize()
}