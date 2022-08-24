package com.limallucas96.feature_one.catsummary

import com.limallucas96.core_presentation.mvi.BaseMVIViewModel

class CatSummaryViewModel :
    BaseMVIViewModel<CatSummaryAction, CatSummaryViewState, CatSummarySideEffect>() {

    override fun createInitialViewState() = CatSummaryViewState()

    override fun handleUserAction(event: CatSummaryAction, currentState: CatSummaryViewState) {
        when (event) {
            CatSummaryAction.ButtonGoToHomeClick -> {
                emitSideEffect(CatSummarySideEffect.NavigateToHome)
            }
            CatSummaryAction.ButtonGoToCatProfileClick -> {
                emitSideEffect(
                    CatSummarySideEffect.NavigateToCatProfile(
                        clearBackStack = true
                    )
                )
            }
            is CatSummaryAction.ViewScreen -> {
                updateViewState {
                    copy(
                        catSummary = "${event.catName}\n${event.catAge}\n",
                        catPhotoUrl = event.catPhotoUrl
                    )
                }
            }
        }
    }

}