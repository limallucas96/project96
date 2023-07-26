package com.example.feature_test_settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.abtest.featureflags.FeatureFlag
import com.example.abtest.providers.FeatureFlagProvider
import com.example.feature_test_settings.databinding.ActivityFeatureTestSettingsEntryPointBinding
import com.limallucas96.core_presentation.base.BaseActivity
import com.limallucas96.core_presentation.mvi.BaseMVINavigationActivity
import javax.inject.Inject

class FeatureTestSettingsEntryPointActivity : BaseActivity<ActivityFeatureTestSettingsEntryPointBinding>() {

    @Inject
    lateinit var provider: FeatureFlagProvider

    override fun inflateBinding(): ActivityFeatureTestSettingsEntryPointBinding {
        return ActivityFeatureTestSettingsEntryPointBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = FeatureFlagAdapter(
            items = FeatureFlag.values(),
            provider = provider,
            checkedListener = { feature, isActivated ->

            }
        )
        binding.recyclerViewTestSettings.adapter = adapter
    }



}