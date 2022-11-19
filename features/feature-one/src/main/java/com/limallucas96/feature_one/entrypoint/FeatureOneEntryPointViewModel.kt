package com.limallucas96.feature_one.entrypoint

import com.limallucas96.core_presentation.mvi.BaseMVIViewModel

class FeatureOneEntryPointViewModel :
    BaseMVIViewModel<FeatureOneEntryPointAction, FeatureOneEntryPointViewState, FeatureOneEntryPointSideEffect>() {

    override fun createInitialViewState() = FeatureOneEntryPointViewState()

    override fun handleUserAction(action: FeatureOneEntryPointAction, currentState: FeatureOneEntryPointViewState) {
        when (action) {
            FeatureOneEntryPointAction.OnCreate -> {
                emitSideEffect(
                    FeatureOneEntryPointSideEffect.NavigateToCatProfileFragment(
                        FEATURE_ONE_ACTIVITY_BACK_STACK
                    )
                )
            }
        }
    }

    companion object {
        private const val FEATURE_ONE_ACTIVITY_BACK_STACK = "FEATURE_ONE_ACTIVITY_BACK_STACK"
    }
}