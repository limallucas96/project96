package com.limallucas96.feature_home.home

import androidx.lifecycle.viewModelScope
import com.limallucas96.core_data.repositories.pet.PetRepository
import com.limallucas96.core_presentation.mvi.BaseMVIViewModel
import com.limallucas96.core_presentation.resourceprovider.ResourcesProvider
import com.limallucas96.feature_home.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val petRepository: PetRepository,
    private val resourcesProvider: ResourcesProvider
) : BaseMVIViewModel<HomeFragmentAction, HomeFragmentViewState, HomeFragmentSideEffect>() {

    override fun createInitialViewState() = HomeFragmentViewState()

    override fun handleUserAction(action: HomeFragmentAction, currentState: HomeFragmentViewState) {
        when (action) {
            HomeFragmentAction.PrimaryButtonClick -> emitSideEffect(HomeFragmentSideEffect.NavigateToFeatureOne)
            HomeFragmentAction.SecondaryButtonClick -> emitSideEffect(HomeFragmentSideEffect.NavigateToFeatureTwo)
            HomeFragmentAction.ViewScreen -> fetchPets()
        }
    }

    private fun fetchPets() {
        viewModelScope.launch {
            petRepository.getPets().collectLatest { petResult ->
                petResult.fold(
                    onSuccess = { pets ->
                        val petCounter = resourcesProvider.getString(R.string.pet_counter, pets.size)
                        updateViewState { copy(petCounter = petCounter) }
                    },
                    onFailure = {
                        updateViewState { copy(petCounter = resourcesProvider.getString(R.string.pet_counter_error)) }
                    }
                )
            }
        }
    }
}