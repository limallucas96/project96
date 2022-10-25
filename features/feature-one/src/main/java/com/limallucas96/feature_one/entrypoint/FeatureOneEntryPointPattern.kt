package com.limallucas96.feature_one.entrypoint

import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewAction
import com.limallucas96.core_presentation.mvi.ViewState

class FeatureOneEntryPointViewState : ViewState

sealed class FeatureOneEntryPointAction : ViewAction {
    object ViewScreen : FeatureOneEntryPointAction()
}

sealed class FeatureOneEntryPointSideEffect : SideEffect {
    data class NavigateToFeatureOneFragment(val backStack: String = "") :
        FeatureOneEntryPointSideEffect()
}