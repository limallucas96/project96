package com.limallucas96.feature_one.catprofile

import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewEvent
import com.limallucas96.core_presentation.mvi.ViewState

data class CatProfileViewState(
    val catName: String = "",
    val catAge: String = "",
    val isContinueButtonEnable: Boolean = false
) : ViewState

sealed class CatProfileEvents : ViewEvent {
    data class OnCatNameChanged(val catName: String = "") : CatProfileEvents()
    data class OnCatAgeChanged(val catAge: String = "") : CatProfileEvents()
    object ButtonContinueClick : CatProfileEvents()
}

sealed class CatProfileSideEffects : SideEffect {
    data class NavigateToCatPicker(
        val catName: String,
        val catAge: String,
        val backStack: String
    ) : CatProfileSideEffects()
}