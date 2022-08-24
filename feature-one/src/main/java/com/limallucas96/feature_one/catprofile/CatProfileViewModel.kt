package com.limallucas96.feature_one.catprofile

import com.limallucas96.core_presentation.mvi.BaseMVIViewModel

class CatProfileViewModel :
    BaseMVIViewModel<CatProfileAction, CatProfileViewState, CatProfileSideEffect>() {

    override fun createInitialViewState() = CatProfileViewState()

    override fun handleUserAction(
        event: CatProfileAction,
        currentState: CatProfileViewState
    ) {
        when (event) {
            is CatProfileAction.OnCatNameChanged -> {
                validateCatProfile(currentState.copy(catName = event.catName))
            }
            is CatProfileAction.OnCatAgeChanged -> {
                validateCatProfile(currentState.copy(catAge = event.catAge))
            }
            CatProfileAction.ButtonContinueClick -> {
                emitSideEffect(
                    CatProfileSideEffect.NavigateToCatPicker(
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