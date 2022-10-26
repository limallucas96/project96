package com.limallucas96.navigator.featuretwo

import android.content.Context
import android.content.Intent
import com.limallucas96.navigator.extensions.toIntent

internal class FeatureTwoNavigatorImpl : FeatureTwoNavigator {

    override fun newIntent(context: Context): Intent {
        return FEATURE_TWO_ENTRY_POINT_ROUTE.toIntent(context)
    }

    companion object {
        private const val FEATURE_TWO_ENTRY_POINT_ROUTE = "entrypoint.FeatureTwoEntryPointActivity.route"
    }

}