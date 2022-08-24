package com.limallucas96.feature_one.catprofile

import com.limallucas96.core_presentation.mvi.BaseMVIViewModel

class CatProfileViewModel :
    BaseMVIViewModel<CatProfileEvents, CatProfileViewState, CatProfileSideEffects>() {

    override fun createInitialViewState() = CatProfileViewState()

    override fun handleEvent(
        event: CatProfileEvents,
        currentState: CatProfileViewState
    ) {
        when (event) {
            is CatProfileEvents.OnCatNameChanged -> {
                validateCatProfile(currentState.copy(catName = event.catName))
            }
            is CatProfileEvents.OnCatAgeChanged -> {
                validateCatProfile(currentState.copy(catAge = event.catAge))
            }
            CatProfileEvents.ButtonContinueClick -> {
                setSideEffect(
                    CatProfileSideEffects.NavigateToCatPicker(
                        currentState.catName,
                        currentState.catAge,
                        CAT_PROFILE_BACK_STACK
                    )
                )
            }
        }
    }

    private fun validateCatProfile(viewState: CatProfileViewState) {
        updateViewState { viewState }
        updateViewState { copy(isContinueButtonEnable = catName.isNotEmpty() && catAge.isNotEmpty()) }
    }

    companion object {
        private const val CAT_PROFILE_BACK_STACK = "CAT_PROFILE_BACK_STACK"
    }

}