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
    private val dispatcher: AppDispatcherProvider,
    private val analytics: Analytics
) : BaseMVIViewModel<SplashAction, SplashViewState, SplashSideEffect>() {

    override fun createInitialViewState() = SplashViewState()

    override fun handleUserAction(action: SplashAction, currentState: SplashViewState) {
        when (action) {
            SplashAction.OnCreate -> {
                analytics.logFirebaseEvent(SPLASH_CREATION_EVENT)
                emitSideEffect(SplashSideEffect.NavigateToHome)
            }
        }
    }

}