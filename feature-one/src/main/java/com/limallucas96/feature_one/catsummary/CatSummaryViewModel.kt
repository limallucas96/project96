package com.limallucas96.feature_one.catsummary

import androidx.lifecycle.viewModelScope
import com.limallucas96.core_common.AppDispatchers
import com.limallucas96.core_data.repositories.PetRepository
import com.limallucas96.core_presentation.mvi.BaseMVIViewModel
import com.limallucas96.core_presentation.resourceprovider.ResourcesProvider
import com.limallucas96.feature_one.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatSummaryViewModel @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val resourcesProvider: ResourcesProvider,
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
                updateViewState {
                    copy(
                        catName = resourcesProvider.getString(R.string.my_cat_name, event.catName),
                        catAge = resourcesProvider.getString(R.string.my_cat_age, event.catAge),
                        catPhotoUrl = event.catPhotoUrl
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
        viewModelScope.launch(appDispatchers.default) {
            petRepository.insertPet(
                petName = currentState.catName,
                petAge = currentState.catAge.toIntOrNull() ?: 0,
                petPhotoUrl = currentState.catPhotoUrl
            )
        }
    }

}