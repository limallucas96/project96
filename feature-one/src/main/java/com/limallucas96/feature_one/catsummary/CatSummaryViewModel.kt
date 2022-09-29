package com.limallucas96.feature_one.catsummary

import com.limallucas96.core_data.repositories.PetRepository
import com.limallucas96.core_presentation.mvi.BaseMVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatSummaryViewModel @Inject constructor(
    private val petRepository: PetRepository
) : BaseMVIViewModel<CatSummaryAction, CatSummaryViewState, CatSummarySideEffect>() {

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
                // TODO Inject resources
                updateViewState {
                    copy(
                        catSummary = "${event.catName}\n${event.catAge}\n",
                        catPhotoUrl = event.catPhotoUrl
                    )
                }
            }
            CatSummaryAction.ButtonSaveLocallyClick -> {
                // TODO
//                petRepository.insertCat(currentState.)
            }
        }
    }

}