package com.limallucas96.feature_one.catpicker

import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewAction
import com.limallucas96.core_presentation.mvi.ViewState

data class CatPickerViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val catName: String = "",
    val catAge: String = "",
    val catUrlPhoto: String = ""
) : ViewState

sealed class CatPickerAction : ViewAction {
    data class InitView(
        val catName: String,
        val catAge: String,
    ) : CatPickerAction()
    object ViewScreen : CatPickerAction()
    object ButtonChooseCatClick : CatPickerAction()
    object ButtonShortNewCat : CatPickerAction()
    object Retry : CatPickerAction()
}

sealed class CatPickerSideEffect : SideEffect {
    data class NavigateToCatSummary(
        val catName: String = "",
        val catAge: String = "",
        val catPhotoUrl: String = "",
        val clearBackStack: Boolean = true
    ) : CatPickerSideEffect()
}