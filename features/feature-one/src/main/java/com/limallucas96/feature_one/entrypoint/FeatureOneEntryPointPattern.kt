package com.limallucas96.feature_one.entrypoint

import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewAction
import com.limallucas96.core_presentation.mvi.ViewState
import com.limallucas96.feature_one.enums.CatProfileProgress

data class FeatureOneEntryPointViewState(
    val toolbarProgress: CatProfileProgress = CatProfileProgress.NONE
) : ViewState

sealed class FeatureOneEntryPointAction : ViewAction {
    object OnCreate : FeatureOneEntryPointAction()
    data class UpdateToolbar(val step: CatProfileProgress) : FeatureOneEntryPointAction()
}

sealed class FeatureOneEntryPointSideEffect : SideEffect {
    data class NavigateToCatProfileFragment(val backStack: String = "") :
        FeatureOneEntryPointSideEffect()
}