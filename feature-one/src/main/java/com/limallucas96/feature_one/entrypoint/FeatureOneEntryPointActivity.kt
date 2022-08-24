package com.limallucas96.feature_one.entrypoint

import androidx.activity.viewModels
import com.limallucas96.core_presentation.mvi.BaseMVINavigationActivity
import com.limallucas96.feature_one.catprofile.CatProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeatureOneEntryPointActivity :
    BaseMVINavigationActivity<FeatureOneEntryPointEvents, FeatureOneEntryPointViewState, FeatureOneEntryPointSideEffects>() {

    override val viewModel: FeatureOneEntryPointViewModel by viewModels()

    override fun onViewReady() {
        viewModel.setEvent(FeatureOneEntryPointEvents.ViewScreen)
    }

    override fun onViewStateUpdated(viewState: FeatureOneEntryPointViewState) {
        // nothing to do here
    }

    override fun onSideEffectReceived(sideEffect: FeatureOneEntryPointSideEffects) {
        when (sideEffect) {
            is FeatureOneEntryPointSideEffects.NavigateToFeatureOneFragment -> {
                navigateTo(CatProfileFragment.newInstance(), sideEffect.backStack)
            }
        }
    }

}