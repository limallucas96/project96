package com.example.abtest.runtime

import com.example.abtest.providers.FeatureFlagProvider
import com.example.abtest.featureflags.Feature
import com.example.abtest.providers.firebase.FirebaseFeatureFlagProvider
import com.example.abtest.providers.runtime.RuntimeFeatureFlagProvider
import com.example.abtest.providers.store.StoreFeatureFlagProvider
import java.util.concurrent.CopyOnWriteArrayList
import javax.inject.Inject

//TODO rename this to AbtestBehavior or something like this

class RuntimeBehaviorImpl @Inject constructor(
    private val runtimeFeatureFlagProvider: RuntimeFeatureFlagProvider,
    private val storeFeatureFlagProvider: StoreFeatureFlagProvider,
    private val firebaseFeatureFlagProvider: FirebaseFeatureFlagProvider,
    private val isDebugBuild: Boolean
) : RuntimeBehavior {

    override val providers: CopyOnWriteArrayList<FeatureFlagProvider> = CopyOnWriteArrayList<FeatureFlagProvider>()

    override fun isFeatureEnabled(feature: Feature): Boolean {
        return providers.filter { it.hasFeature(feature) }
            .sortedBy(FeatureFlagProvider::priority)
            .firstOrNull()
            ?.isFeatureEnabled(feature)
            ?: feature.defaultValue
    }

    override fun addProvider(provider: FeatureFlagProvider) {
        providers.add(provider)
    }

    override fun initialize() {
        if (isDebugBuild) {
//            if (runtimeFeatureFlagProvider.isFeatureEnabled(TestSetting.DEBUG_FIREBASE)) {
//                addProvider(firebaseFeatureFlagProvider)
//            }
        } else {
            addProvider(storeFeatureFlagProvider)
            addProvider(firebaseFeatureFlagProvider)
        }
    }

}