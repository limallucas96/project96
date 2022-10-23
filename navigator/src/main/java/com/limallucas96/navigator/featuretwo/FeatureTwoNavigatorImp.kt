package com.limallucas96.navigator.featuretwo

import android.content.Context
import android.content.Intent
import com.limallucas96.navigator.commons.toIntent

internal class FeatureTwoNavigatorImp : FeatureTwoNavigator {

    override fun newIntent(context: Context): Intent {
        return FEATURE_TWO_ENTRY_POINT_ROUTE.toIntent(context)
    }

    companion object {
        const val FEATURE_TWO_ENTRY_POINT_ROUTE = "entrypoint.FeatureTwoEntryPointActivity.route"
    }

}