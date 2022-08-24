package com.limallucas96.feature_one.entrypoint

import com.limallucas96.core_presentation.mvi.BaseMVIViewModel

class FeatureOneEntryPointViewModel :
    BaseMVIViewModel<FeatureOneEntryPointEvents, FeatureOneEntryPointViewState, FeatureOneEntryPointSideEffects>() {

    override fun createInitialViewState() = FeatureOneEntryPointViewState()

    override fun handleEvent(
        event: FeatureOneEntryPointEvents,
        currentState: FeatureOneEntryPointViewState
    ) {
        when (event) {
            FeatureOneEntryPointEvents.ViewScreen -> {
                setSideEffect(
                    FeatureOneEntryPointSideEffects.NavigateToFeatureOneFragment(
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