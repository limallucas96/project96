package com.limallucas96.feature_home.entrypoint

import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewAction
import com.limallucas96.core_presentation.mvi.ViewState

data class HomeViewState(
    val petCounter: String = ""
) : ViewState

sealed class HomeAction : ViewAction {
    object ViewScreen : HomeAction()
    object PrimaryButtonClick : HomeAction()
    object SecondaryButtonClick : HomeAction()
}

sealed class HomeSideEffect : SideEffect {
    object NavigateToFeatureOne : HomeSideEffect()
    object NavigateToFeatureTwo : HomeSideEffect()
}