package com.example.abtest.providers.firebase

import com.example.abtest.providers.FeatureFlagProvider
import com.example.abtest.featureflags.Priority
import com.example.abtest.featureflags.Feature
import com.example.abtest.featureflags.FeatureFlag
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

class FirebaseFeatureFlagProvider(private val isDebugBuild: Boolean) : FeatureFlagProvider {

    private val remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

    init {
        val cacheExpiration: Long = if (isDebugBuild) 0 else 3600
        remoteConfig.setConfigSettingsAsync(
            FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(cacheExpiration)
                .build()
        )
    }

    override val priority: Int = Priority.MAX_PRIORITY.ordinal

    override fun isFeatureEnabled(feature: Feature): Boolean = remoteConfig.getBoolean(feature.key)

    override fun hasFeature(feature: Feature): Boolean {
        return when (feature) {
            FeatureFlag.DARK_MODE -> true
            else -> false
        }
    }
}