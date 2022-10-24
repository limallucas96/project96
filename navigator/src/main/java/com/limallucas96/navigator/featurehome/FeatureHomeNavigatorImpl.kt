package com.limallucas96.navigator.featurehome

import android.content.Context
import android.content.Intent
import com.limallucas96.navigator.commons.toIntent
import javax.inject.Inject

internal class FeatureHomeNavigatorImpl @Inject constructor()  : FeatureHomeNavigator {

    override fun newIntent(context: Context): Intent {
        return FEATURE_HOME_ENTRY_POINT_ROUTE.toIntent(context)
    }

    companion object {
        private const val FEATURE_HOME_ENTRY_POINT_ROUTE = "entrypoint.FeatureHomeEntryPointActivity.route"
    }

}