package com.limallucas96.feature_home.entrypoint

import com.limallucas96.core_presentation.mvi.BaseMVIViewModel

class FeatureHomeEntryPointViewModel :
    BaseMVIViewModel<FeatureHomeEntryPointAction, FeatureHomeEntryPointViewState, FeatureHomeEntryPointSideEffect>() {

    override fun createInitialViewState() = FeatureHomeEntryPointViewState()

    override fun handleUserAction(event: FeatureHomeEntryPointAction, currentState: FeatureHomeEntryPointViewState) {
        when (event) {
            FeatureHomeEntryPointAction.ViewScreen -> emitSideEffect(FeatureHomeEntryPointSideEffect.NavigateToHomeFragment)
        }
    }

}