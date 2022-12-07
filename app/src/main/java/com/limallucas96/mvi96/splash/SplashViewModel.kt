package com.limallucas96.mvi96.splash

import androidx.lifecycle.viewModelScope
import com.example.analytics.analytics.Analytics
import com.example.analytics.analytics.Events.SPLASH_CREATION_EVENT
import com.limallucas96.core_common.AppDispatcherProvider
import com.limallucas96.core_presentation.mvi.BaseMVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val analytics: Analytics,
    private val dispatcher: AppDispatcherProvider
) : BaseMVIViewModel<SplashAction, SplashViewState, SplashSideEffect>() {

    override fun createInitialViewState() = SplashViewState()

    override fun handleUserAction(action: SplashAction, currentState: SplashViewState) {
        when (action) {
            SplashAction.OnCreate -> {
                analytics.logFirebaseEvent(SPLASH_CREATION_EVENT)
                navigateToHome()
            }
        }
    }

    private fun navigateToHome() {
        viewModelScope.launch {
            delay(5000L) // fake a delay of 5 seconds in splash screen before emit navigate side effect
            emitSideEffect(SplashSideEffect.NavigateToHome)
        }
    }

}