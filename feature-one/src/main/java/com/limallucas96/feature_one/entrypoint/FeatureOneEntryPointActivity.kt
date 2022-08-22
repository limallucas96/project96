package com.limallucas96.feature_one.entrypoint

import androidx.activity.viewModels
import com.limallucas96.core_presentation.mvi.BaseMVINavigationActivity
import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.feature_one.featureone.FeatureOneFragment

class FeatureOneEntryPointActivity :
    BaseMVINavigationActivity<FeatureOneEntryPointEvents, FeatureOneEntryPointViewState, FeatureOneEntryPointSideEffects>() {

    override val viewModel: FeatureOneEntryPointViewModel by viewModels()

    override fun onViewReady() {
        viewModel.setEvent(FeatureOneEntryPointEvents.ViewScreen)
    }

    override fun onViewStateUpdated(viewState: FeatureOneEntryPointViewState) {
        // nothing to do here
    }

    override fun onSideEffectReceived(sideEffect: SideEffect) {
        when (sideEffect) {
            is FeatureOneEntryPointSideEffects.NavigateToFeatureOneFragment -> {
                navigateTo(FeatureOneFragment.newInstance(), sideEffect.backStack)
            }
        }
    }

}