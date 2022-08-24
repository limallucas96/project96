package com.limallucas96.feature_one.catpicker

import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewEvent
import com.limallucas96.core_presentation.mvi.ViewState

data class CatPickerViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val catName: String = "",
    val catAge: String = "",
    val catUrlPhoto: String = ""
) : ViewState

sealed class CatPickerEvents : ViewEvent {
    data class InitView(
        val catName: String,
        val catAge: String,
    ) : CatPickerEvents()
    object ViewScreen : CatPickerEvents()
    object ButtonChooseCatClick : CatPickerEvents()
    object ButtonShortNewCat : CatPickerEvents()
    object Retry : CatPickerEvents()
}

sealed class CatPickerSideEffects : SideEffect {
    data class NavigateToCatSummary(
        val catName: String = "",
        val catAge: String = "",
        val catPhotoUrl: String = "",
        val clearBackStack: Boolean = true
    ) : CatPickerSideEffects()
}