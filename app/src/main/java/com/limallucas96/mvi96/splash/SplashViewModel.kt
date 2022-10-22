package com.limallucas96.mvi96.splash

import androidx.lifecycle.viewModelScope
import com.limallucas96.core_presentation.mvi.BaseMVIViewModel
import com.limallucas96.core_presentation.resourceprovider.ResourcesProvider
import com.limallucas96.core_sharedpreferences.sharedpreferences.AppSharedPreferences
import com.limallucas96.mvi96.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sharedPreferencesRepository: AppSharedPreferences,
    private val resourcesProvider: ResourcesProvider
) : BaseMVIViewModel<SplashAction, SplashViewState, SplashSideEffect>() {

    override fun createInitialViewState() = SplashViewState()

    private fun fetchPets() {
        viewModelScope.launch {
            // TODO Fetch pet counter from sharedPref
            val petCounter = resourcesProvider.getString(R.string.pet_counter, "10")
            updateViewState { copy(petCounter = petCounter) }
        }
    }

    override fun handleUserAction(event: SplashAction, currentState: SplashViewState) {
        when (event) {
            SplashAction.PrimaryButtonClick -> emitSideEffect(SplashSideEffect.NavigateToFeatureOne)
            SplashAction.SecondaryButtonClick -> emitSideEffect(SplashSideEffect.NavigateToFeatureTwo)
            SplashAction.ViewScreen -> fetchPets()
        }
    }

}