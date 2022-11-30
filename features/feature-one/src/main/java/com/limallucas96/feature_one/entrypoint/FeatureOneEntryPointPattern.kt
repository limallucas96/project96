package com.limallucas96.feature_one.entrypoint

import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewAction
import com.limallucas96.core_presentation.mvi.ViewState
import com.limallucas96.feature_one.enums.CatProfileProgress

data class FeatureOneEntryPointViewState(
    val isProgressBarVisible: Boolean = false,
    val progressBarStep: Int = 0,
    val progressBarMax: Int = 0,
    val isToolbarVisible: Boolean = false,
    val toolbarTitle: String = ""
) : ViewState

sealed class FeatureOneEntryPointAction : ViewAction {
    object OnCreate : FeatureOneEntryPointAction()
    data class UpdateToolbar(val catProfileProgress: CatProfileProgress) : FeatureOneEntryPointAction()
}

sealed class FeatureOneEntryPointSideEffect : SideEffect {
    data class NavigateToCatProfileFragment(val backStack: String = "") :
        FeatureOneEntryPointSideEffect()
}