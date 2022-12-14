package com.limallucas96.feature_one.catsummary

import androidx.lifecycle.viewModelScope
import com.limallucas96.core_common.AppDispatcherProvider
import com.limallucas96.core_data.repositories.pet.PetRepository
import com.limallucas96.core_presentation.mvi.BaseMVIViewModel
import com.limallucas96.core_presentation.resourceprovider.ResourcesProvider
import com.limallucas96.feature_one.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatSummaryViewModel @Inject constructor(
    private val dispatcher: AppDispatcherProvider,
    private val resourcesProvider: ResourcesProvider,
    private val petRepository: PetRepository
) : BaseMVIViewModel<CatSummaryAction, CatSummaryViewState, CatSummarySideEffect>() {

    override fun createInitialViewState() = CatSummaryViewState()

    override fun handleUserAction(action: CatSummaryAction, currentState: CatSummaryViewState) {
        when (action) {
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
            is CatSummaryAction.OnCreate -> {
                updateViewState {
                    copy(
                        catName = resourcesProvider.getString(R.string.my_cat_name, action.catName),
                        catAge = resourcesProvider.getString(R.string.my_cat_age, action.catAge),
                        catPhotoUrl = action.catPhotoUrl
                    )
                }
            }
            CatSummaryAction.ButtonSaveLocallyClick -> {
                insertNewPet(currentState)
                emitSideEffect(CatSummarySideEffect.ShowExitConfirmationDialog)
            }
            CatSummaryAction.DialogConfirmExitClick -> {
                emitSideEffect(CatSummarySideEffect.NavigateToHome)
            }
        }
    }

    private fun insertNewPet(currentState: CatSummaryViewState) {
        viewModelScope.launch(dispatcher.default) {
            petRepository.insertPet(
                petName = currentState.catName,
                petAge = currentState.catAge.toIntOrNull() ?: 0,
                petPhotoUrl = currentState.catPhotoUrl
            )
        }
    }

}