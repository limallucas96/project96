package com.example.abtest.providers.store

import com.example.abtest.providers.FeatureFlagProvider
import com.example.abtest.featureflags.Priority
import com.example.abtest.featureflags.Feature
import com.example.abtest.featureflags.FeatureFlag
import com.example.abtest.featureflags.TestSetting
import javax.inject.Inject

class StoreFeatureFlagProvider @Inject constructor(): FeatureFlagProvider {

    override val priority = Priority.MEDIUM_PRIORITY.ordinal

    override fun isFeatureEnabled(feature: Feature): Boolean {
        return if (feature is FeatureFlag) {
            // No "else" branch here -> choosing the default
            // option for release must be an explicit choice
            when (feature) {
                FeatureFlag.DARK_MODE -> false
            }
        } else {
            // TestSettings should never be shipped to users
            when (feature as TestSetting) {
                else -> false
            }
        }
    }

    override fun hasFeature(feature: Feature): Boolean = true
}