package com.limallucas96.mvi96.splash

import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewAction
import com.limallucas96.core_presentation.mvi.ViewState

class SplashViewState() : ViewState

sealed class SplashAction : ViewAction {
    object ViewScreen : SplashAction()
}

sealed class SplashSideEffect : SideEffect {
    object NavigateToHome : SplashSideEffect()
}