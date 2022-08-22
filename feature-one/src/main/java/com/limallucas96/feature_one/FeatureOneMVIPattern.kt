package com.limallucas96.feature_one

import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewEvent
import com.limallucas96.core_presentation.mvi.ViewState

data class FeatureOneViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val screenText: String = ""
) : ViewState

sealed class FeatureOneEvents : ViewEvent {
    object ViewScreen : FeatureOneEvents()
}

sealed class FeatureOneSideEffects : SideEffect {
    object ShowToast : FeatureOneSideEffects()
}