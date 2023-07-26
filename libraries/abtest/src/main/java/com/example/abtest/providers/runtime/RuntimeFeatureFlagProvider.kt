package com.example.abtest.providers.runtime

import com.example.abtest.providers.FeatureFlagProvider
import com.example.abtest.featureflags.Priority
import com.example.abtest.featureflags.Feature
import com.limallucas96.core_sharedpreferences.sharedpreferences.AppSharedPreferences
import javax.inject.Inject

class RuntimeFeatureFlagProvider @Inject constructor(
    private val appSharedPreferences: AppSharedPreferences
): FeatureFlagProvider {

    override val priority = Priority.SMALL_PRIORITY.ordinal

    override fun isFeatureEnabled(feature: Feature): Boolean =
        appSharedPreferences.getBoolean(feature.key, feature.defaultValue)

    override fun hasFeature(feature: Feature): Boolean = true

    fun setFeatureEnabled(feature: Feature, enabled: Boolean) =
        appSharedPreferences.putBoolean(feature.key, enabled)
}