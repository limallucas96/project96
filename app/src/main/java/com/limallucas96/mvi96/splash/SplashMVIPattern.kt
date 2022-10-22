package com.limallucas96.mvi96.splash

import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewAction
import com.limallucas96.core_presentation.mvi.ViewState

data class SplashViewState(
    val petCounter: String = ""
) : ViewState

sealed class SplashAction : ViewAction {
    object ViewScreen : SplashAction()
    object PrimaryButtonClick : SplashAction()
    object SecondaryButtonClick : SplashAction()
}

sealed class SplashSideEffect : SideEffect {
    object NavigateToFeatureOne : SplashSideEffect()
    object NavigateToFeatureTwo : SplashSideEffect()
}