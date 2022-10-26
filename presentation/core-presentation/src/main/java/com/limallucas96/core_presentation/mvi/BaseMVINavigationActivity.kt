package com.limallucas96.core_presentation.mvi

import com.limallucas96.core_presentation.databinding.ActivityBaseNavigationBinding
import com.limallucas96.core_presentation.extensions.isLastFragment
import com.limallucas96.core_presentation.extensions.removeAllFragmentsFromBackStack

abstract class BaseMVINavigationActivity<UserAction : ViewAction, UIViewState : ViewState, UISideEffect : SideEffect> :
    BaseMVIActivity<ActivityBaseNavigationBinding, UserAction, UIViewState, UISideEffect>() {

    override fun inflateBinding() = ActivityBaseNavigationBinding.inflate(layoutInflater)

    override fun onBackPressed() {
        when {
            isLastFragment() -> {
                removeAllFragmentsFromBackStack()
                finish()
            }
            else -> super.onBackPressed()
        }
    }

}