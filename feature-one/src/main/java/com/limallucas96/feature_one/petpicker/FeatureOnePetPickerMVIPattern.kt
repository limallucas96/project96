package com.limallucas96.feature_one.petpicker

import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewEvent
import com.limallucas96.core_presentation.mvi.ViewState

data class FeatureOnePetPickerViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false
) : ViewState

sealed class FeatureOnePetPickerEvents : ViewEvent {
}

sealed class FeatureOnePetPickerSideEffects : SideEffect {
}