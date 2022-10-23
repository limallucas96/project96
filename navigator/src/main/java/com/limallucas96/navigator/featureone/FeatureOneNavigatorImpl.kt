package com.limallucas96.navigator.featureone

import android.content.Context
import android.content.Intent
import com.limallucas96.navigator.commons.toIntent
import javax.inject.Inject

internal class FeatureOneNavigatorImpl @Inject constructor() : FeatureOneNavigator {

    override fun newIntent(context: Context): Intent {
        return FEATURE_ONE_ENTRY_POINT_ROUTE.toIntent(context)
    }

    companion object {
        const val FEATURE_ONE_ENTRY_POINT_ROUTE = "entrypoint.FeatureOneEntryPointActivity.route"
    }

}