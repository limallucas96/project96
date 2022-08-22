package com.limallucas96.feature_one.petprofile

import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewEvent
import com.limallucas96.core_presentation.mvi.ViewState

data class FeatureOnePetProfileViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val screenText: String = ""
) : ViewState

sealed class FeatureOnePetProfileEvents : ViewEvent {
    object ViewScreen : FeatureOnePetProfileEvents()
}

sealed class FeatureOnePetProfileSideEffects : SideEffect {
    object ShowToast : FeatureOnePetProfileSideEffects()
}