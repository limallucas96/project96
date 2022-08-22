package com.limallucas96.navigator.featureone

import android.content.Context
import android.content.Intent
import com.limallucas96.feature_one.entrypoint.FeatureOneEntryPointActivity
import javax.inject.Inject

internal class FeatureOneNavigatorImpl @Inject constructor() : FeatureOneNavigator {

    override fun newIntent(context: Context): Intent {
        return Intent(context, FeatureOneEntryPointActivity::class.java)
    }

}