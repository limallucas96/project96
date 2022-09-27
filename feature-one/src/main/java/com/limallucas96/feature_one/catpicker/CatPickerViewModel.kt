package com.limallucas96.feature_one.catpicker

import androidx.lifecycle.viewModelScope
import com.limallucas96.core_common.AppDispatcherProvider
import com.limallucas96.core_data.repositories.cat.CatRepository
import com.limallucas96.core_presentation.mvi.BaseMVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class CatPickerViewModel @Inject constructor(
    private val dispatcher: AppDispatcherProvider,
    private val catRepository: CatRepository
) : BaseMVIViewModel<CatPickerAction, CatPickerViewState, CatPickerSideEffect>() {

    override fun createInitialViewState() = CatPickerViewState()

    override fun handleUserAction(
        event: CatPickerAction,
        currentState: CatPickerViewState
    ) {
        when (event) {
            CatPickerAction.ViewScreen,
            CatPickerAction.ButtonShortNewCat,
            CatPickerAction.Retry -> {
                fetchCats()
            }
            CatPickerAction.ButtonChooseCatClick -> {
                navigateToCatSummary(
                    currentState.catName,
                    currentState.catAge,
                    currentState.catUrlPhoto
                )
            }
            is CatPickerAction.InitView -> {
                updateViewState { copy(catName = event.catName, catAge = event.catAge) }
            }
        }
    }

    private fun fetchCats() {
        updateViewState { copy(isLoading = true, isError = false) }
        viewModelScope.launch(dispatcher.default) {
            catRepository.getCats().fold(
                onSuccess = { cats ->
                    val url = cats.firstOrNull()?.url.orEmpty()
                    updateViewState { copy(isLoading = false, catUrlPhoto = url) }
                },
                onFailure = {
                    updateViewState { copy(isLoading = false, isError = true) }
                }
            )
        }
    }

    private fun navigateToCatSummary(catName: String, catAge: String, catPhotoUrl: String) {
        emitSideEffect(
            CatPickerSideEffect.NavigateToCatSummary(
                catName = catName,
                catAge = catAge,
                catPhotoUrl = catPhotoUrl,
                clearBackStack = true
            )
        )
    }

}