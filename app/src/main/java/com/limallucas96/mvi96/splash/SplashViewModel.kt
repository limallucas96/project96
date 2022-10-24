package com.limallucas96.mvi96.splash

import androidx.lifecycle.viewModelScope
import com.limallucas96.core_presentation.mvi.BaseMVIViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : BaseMVIViewModel<SplashAction, SplashViewState, SplashSideEffect>() {

    override fun createInitialViewState() = SplashViewState()

    override fun handleUserAction(event: SplashAction, currentState: SplashViewState) {
        when (event) {
            SplashAction.ViewScreen -> navigateToHome()
        }
    }

    private fun navigateToHome() {
        viewModelScope.launch {
            delay(5000L) // fake a delay of 5 seconds in splash screen before emit navigate side effect
            emitSideEffect(SplashSideEffect.NavigateToHome)
        }
    }

}