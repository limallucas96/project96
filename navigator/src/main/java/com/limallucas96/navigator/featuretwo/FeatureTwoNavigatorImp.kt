package com.limallucas96.navigator.featuretwo

import android.content.Context
import android.content.Intent
import com.limallucas96.feature_two.FeatureTwoActivity

internal class FeatureTwoNavigatorImp : FeatureTwoNavigator {

    override fun newIntent(context: Context): Intent {
        return Intent(context, FeatureTwoActivity::class.java)
    }
}