package com.limallucas96.feature_one.catsummary

import com.limallucas96.core_presentation.mvi.BaseMVIViewModel

class CatSummaryViewModel :
    BaseMVIViewModel<CatSummaryEvents, CatSummaryViewState, CatSummarySideEffects>() {

    override fun createInitialViewState() = CatSummaryViewState()

    override fun handleEvent(event: CatSummaryEvents, currentState: CatSummaryViewState) {
        when (event) {
            CatSummaryEvents.ButtonGoToHomeClick -> {
                setSideEffect(CatSummarySideEffects.NavigateToHome)
            }
            CatSummaryEvents.ButtonGoToCatProfileClick -> {
                setSideEffect(
                    CatSummarySideEffects.NavigateToCatProfile(
                        clearBackStack = true
                    )
                )
            }
            is CatSummaryEvents.ViewScreen -> {
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