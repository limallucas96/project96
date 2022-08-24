package com.limallucas96.feature_one.catprofile

import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewAction
import com.limallucas96.core_presentation.mvi.ViewState

data class CatProfileViewState(
    val catName: String = "",
    val catAge: String = "",
    val isContinueButtonEnable: Boolean = false
) : ViewState

sealed class CatProfileAction : ViewAction {
    data class OnCatNameChanged(val catName: String = "") : CatProfileAction()
    data class OnCatAgeChanged(val catAge: String = "") : CatProfileAction()
    object ButtonContinueClick : CatProfileAction()
}

sealed class CatProfileSideEffect : SideEffect {
    data class NavigateToCatPicker(
        val catName: String,
        val catAge: String,
        val backStack: String
    ) : CatProfileSideEffect()
}