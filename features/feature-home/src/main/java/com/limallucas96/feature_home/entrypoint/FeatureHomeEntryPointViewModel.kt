package com.limallucas96.feature_home.entrypoint

import com.limallucas96.core_presentation.mvi.BaseMVIViewModel

class FeatureHomeEntryPointViewModel :
    BaseMVIViewModel<FeatureHomeEntryPointAction, FeatureHomeEntryPointViewState, FeatureHomeEntryPointSideEffect>() {

    override fun createInitialViewState() = FeatureHomeEntryPointViewState()

    override fun handleUserAction(action: FeatureHomeEntryPointAction, currentState: FeatureHomeEntryPointViewState) {
        when (action) {
            FeatureHomeEntryPointAction.OnCreate -> {
                emitSideEffect(FeatureHomeEntryPointSideEffect.NavigateToHomeFragment)
            }
        }
    }

}