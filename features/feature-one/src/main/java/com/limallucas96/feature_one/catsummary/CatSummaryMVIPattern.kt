package com.limallucas96.feature_one.catsummary

import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewAction
import com.limallucas96.core_presentation.mvi.ViewState


data class CatSummaryViewState(
    val catName: String = "",
    val catAge: String = "",
    val catPhotoUrl: String = ""
) : ViewState

sealed class CatSummaryAction : ViewAction {
    data class ViewScreen(
        val catName: String,
        val catAge: String,
        val catPhotoUrl: String
    ) : CatSummaryAction()

    object ButtonGoToHomeClick : CatSummaryAction()
    object ButtonGoToCatProfileClick : CatSummaryAction()
    object ButtonSaveLocallyClick : CatSummaryAction()
    object DialogConfirmExitClick : CatSummaryAction()
}

sealed class CatSummarySideEffect : SideEffect {
    object NavigateToHome : CatSummarySideEffect()
    object ShowExitConfirmationDialog : CatSummarySideEffect()
    data class NavigateToCatProfile(
        val clearBackStack: Boolean
    ) : CatSummarySideEffect()
}