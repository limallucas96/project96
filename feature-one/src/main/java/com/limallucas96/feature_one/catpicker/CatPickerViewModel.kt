package com.limallucas96.feature_one.catpicker

import androidx.lifecycle.viewModelScope
import com.limallucas96.core_common.wrappers.ResultWrapper
import com.limallucas96.core_data.repositories.cat.CatRepository
import com.limallucas96.core_presentation.mvi.BaseMVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatPickerViewModel @Inject constructor(
    private val catRepository: CatRepository
) :
    BaseMVIViewModel<CatPickerEvents, CatPickerViewState, CatPickerSideEffects>() {

    override fun createInitialViewState() = CatPickerViewState()

    override fun handleEvent(
        event: CatPickerEvents,
        currentState: CatPickerViewState
    ) {
        when (event) {
            CatPickerEvents.ViewScreen,
            CatPickerEvents.ButtonShortNewCat,
            CatPickerEvents.Retry -> {
                fetchCats()
            }
            CatPickerEvents.ButtonChooseCatClick -> {
                navigateToCatSummary(
                    currentState.catName,
                    currentState.catAge,
                    currentState.catUrlPhoto
                )
            }
            is CatPickerEvents.InitView -> {
                updateViewState { copy(catName = event.catName, catAge = event.catAge) }
            }
        }
    }

    private fun fetchCats() {
        updateViewState { copy(isLoading = true) }
        viewModelScope.launch {
            val url =
                (catRepository.getCats() as ResultWrapper.Success).value.firstOrNull()?.url.orEmpty()
            updateViewState { copy(isLoading = false, catUrlPhoto = url) }
        }
    }

    private fun navigateToCatSummary(catName: String, catAge: String, catPhotoUrl: String) {
        setSideEffect(
            CatPickerSideEffects.NavigateToCatSummary(
                catName = catName,
                catAge = catAge,
                catPhotoUrl = catPhotoUrl,
                clearBackStack = true
            )
        )
    }

    companion object {
        private const val CAT_PICKER_BACK_STACK = "CAT_PICKER_BACK_STACK"
    }

}