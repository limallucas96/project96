package com.limallucas96.feature_one.entrypoint

import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewEvent
import com.limallucas96.core_presentation.mvi.ViewState

class FeatureOneEntryPointViewState : ViewState

sealed class FeatureOneEntryPointEvents : ViewEvent {
    object ViewScreen : FeatureOneEntryPointEvents()
}

sealed class FeatureOneEntryPointSideEffects : SideEffect {
    data class NavigateToFeatureOneFragment(val backStack: String = "") :
        FeatureOneEntryPointSideEffects()
}