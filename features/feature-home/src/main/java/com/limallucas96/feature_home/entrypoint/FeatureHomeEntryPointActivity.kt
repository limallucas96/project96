package com.limallucas96.feature_home.entrypoint

import android.os.Bundle
import androidx.activity.viewModels
import com.limallucas96.core_presentation.mvi.BaseMVINavigationActivity
import com.limallucas96.feature_home.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeatureHomeEntryPointActivity :
    BaseMVINavigationActivity<FeatureHomeEntryPointAction, FeatureHomeEntryPointViewState, FeatureHomeEntryPointSideEffect>() {

    override val viewModel: FeatureHomeEntryPointViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.dispatch(FeatureHomeEntryPointAction.ViewScreen)
    }

    override fun onViewStateUpdated(viewState: FeatureHomeEntryPointViewState) {
        // nothing to do here
        // TODO implement this in base so we dont need to implement her
    }

    override fun onSideEffectReceived(sideEffect: FeatureHomeEntryPointSideEffect) {
        when (sideEffect) {
            FeatureHomeEntryPointSideEffect.NavigateToHomeFragment -> {
                navigateTo(
                    fragment = HomeFragment.newInstance()
                )
            }
        }
    }

}