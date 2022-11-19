package com.limallucas96.feature_home.entrypoint

import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewAction
import com.limallucas96.core_presentation.mvi.ViewState

class FeatureHomeEntryPointViewState(
) : ViewState

sealed class FeatureHomeEntryPointAction : ViewAction {
    object OnCreate : FeatureHomeEntryPointAction()
}

sealed class FeatureHomeEntryPointSideEffect : SideEffect {
    object NavigateToHomeFragment : FeatureHomeEntryPointSideEffect()
}