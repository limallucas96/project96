package com.limallucas96.core_presentation.mvi

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.limallucas96.core_presentation.R

abstract class BaseMVINavigationFragment<VB : ViewBinding, UserAction : ViewAction, UIViewState : ViewState, UISideEffect : SideEffect> :
    BaseMVIFragment<VB, UserAction, UIViewState, UISideEffect>() {

    fun navigateTo(
        fragment: Fragment,
        backStack: String? = null,
        replace: Boolean = false,
        enterAnimation: Int = R.anim.slide_in_right,
        exitAnimation: Int = R.anim.slide_out_right,
        clearStack: Boolean = false,
        clearTop: Boolean = false
    ) {
        if (activity is BaseMVINavigationActivity<*, *, *>) {
            (activity as BaseMVINavigationActivity<*, *, *>).navigateTo(
                fragment,
                backStack,
                replace,
                enterAnimation,
                exitAnimation,
                clearStack,
                clearTop
            )
        }
    }
}