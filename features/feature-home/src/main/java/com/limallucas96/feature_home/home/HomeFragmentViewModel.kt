package com.limallucas96.feature_home.home

import androidx.lifecycle.viewModelScope
import com.limallucas96.core_presentation.mvi.BaseMVIViewModel
import com.limallucas96.core_presentation.resourceprovider.ResourcesProvider
import com.limallucas96.feature_home.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val resourcesProvider: ResourcesProvider
) : BaseMVIViewModel<HomeFragmentAction, HomeFragmentViewState, HomeFragmentSideEffect>() {

    override fun createInitialViewState() = HomeFragmentViewState()

    override fun handleUserAction(event: HomeFragmentAction, currentState: HomeFragmentViewState) {
        when (event) {
            HomeFragmentAction.PrimaryButtonClick -> emitSideEffect(HomeFragmentSideEffect.NavigateToFeatureOne)
            HomeFragmentAction.SecondaryButtonClick -> emitSideEffect(HomeFragmentSideEffect.NavigateToFeatureTwo)
            HomeFragmentAction.ViewScreen -> fetchPets()
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