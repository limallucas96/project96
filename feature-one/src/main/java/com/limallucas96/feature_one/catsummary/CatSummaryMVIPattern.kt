package com.limallucas96.feature_one.catsummary

import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewEvent
import com.limallucas96.core_presentation.mvi.ViewState


data class CatSummaryViewState(
    val catSummary: String = "",
    val catPhotoUrl: String = ""
) : ViewState

sealed class CatSummaryEvents : ViewEvent {
    data class ViewScreen(
        val catName: String,
        val catAge: String,
        val catPhotoUrl: String
    ) : CatSummaryEvents()

    object ButtonGoToHomeClick : CatSummaryEvents()
    object ButtonGoToCatProfileClick : CatSummaryEvents()
}

sealed class CatSummarySideEffects : SideEffect {
    object NavigateToHome : CatSummarySideEffects()
    data class NavigateToCatProfile(
        val clearBackStack: Boolean
    ) : CatSummarySideEffects()
}