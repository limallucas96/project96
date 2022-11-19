package com.limallucas96.feature_home.home

import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewAction
import com.limallucas96.core_presentation.mvi.ViewState

data class HomeFragmentViewState(
    val petCounter: String = ""
) : ViewState

sealed class HomeFragmentAction : ViewAction {
    object OnCreate : HomeFragmentAction()
    object PrimaryButtonClick : HomeFragmentAction()
    object SecondaryButtonClick : HomeFragmentAction()
}

sealed class HomeFragmentSideEffect : SideEffect {
    object NavigateToFeatureOne : HomeFragmentSideEffect()
    object NavigateToFeatureTwo : HomeFragmentSideEffect()
}