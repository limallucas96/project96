package com.limallucas96.feature_one.entrypoint

import androidx.activity.viewModels
import com.limallucas96.core_presentation.mvi.BaseMVINavigationActivity
import com.limallucas96.feature_one.catprofile.CatProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeatureOneEntryPointActivity :
    BaseMVINavigationActivity<FeatureOneEntryPointAction, FeatureOneEntryPointViewState, FeatureOneEntryPointSideEffect>() {

    override val viewModel: FeatureOneEntryPointViewModel by viewModels()

    override fun onViewReady() {
        viewModel.dispatch(FeatureOneEntryPointAction.ViewScreen)
    }

    override fun onViewStateUpdated(viewState: FeatureOneEntryPointViewState) {
        // nothing to do here
    }

    override fun onSideEffectReceived(sideEffect: FeatureOneEntryPointSideEffect) {
        when (sideEffect) {
            is FeatureOneEntryPointSideEffect.NavigateToFeatureOneFragment -> {
                navigateTo(CatProfileFragment.newInstance(), sideEffect.backStack)
            }
        }
    }

}