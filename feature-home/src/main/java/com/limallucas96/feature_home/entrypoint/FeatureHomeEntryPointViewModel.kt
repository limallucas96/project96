package com.limallucas96.feature_home.entrypoint

import androidx.lifecycle.viewModelScope
import com.limallucas96.core_presentation.mvi.BaseMVIViewModel
import com.limallucas96.core_presentation.resourceprovider.ResourcesProvider
import com.limallucas96.feature_home.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeatureHomeEntryPointViewModel @Inject constructor(
    private val resourcesProvider: ResourcesProvider
) :
    BaseMVIViewModel<HomeAction, HomeViewState, HomeSideEffect>() {

    override fun createInitialViewState() = HomeViewState()

    override fun handleUserAction(event: HomeAction, currentState: HomeViewState) {
        when (event) {
            HomeAction.PrimaryButtonClick -> emitSideEffect(HomeSideEffect.NavigateToFeatureOne)
            HomeAction.SecondaryButtonClick -> emitSideEffect(HomeSideEffect.NavigateToFeatureTwo)
            HomeAction.ViewScreen -> fetchPets()
        }
    }

    private fun fetchPets() {
        viewModelScope.launch {
            // TODO Fetch pet counter from sharedPref
            val petCounter = resourcesProvider.getString(R.string.pet_counter, "10")
            updateViewState { copy(petCounter = petCounter) }
        }
    }

}