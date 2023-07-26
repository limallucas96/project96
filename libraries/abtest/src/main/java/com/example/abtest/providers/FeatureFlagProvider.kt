package com.example.abtest.providers

import com.example.abtest.featureflags.Feature

interface FeatureFlagProvider {
    val priority: Int
    fun isFeatureEnabled(feature: Feature): Boolean
    fun hasFeature(feature: Feature): Boolean
}