package com.limallucas96.core_presentation.mvi

import androidx.fragment.app.Fragment
import com.limallucas96.core_presentation.R
import com.limallucas96.core_presentation.databinding.ActivityBaseNavigationBinding
import com.limallucas96.core_presentation.extensions.*

abstract class BaseMVINavigationActivity<UIEvent : ViewEvent, UIViewState : ViewState, UISideEffect : SideEffect> :
    BaseMVIActivity<ActivityBaseNavigationBinding, UIEvent, UIViewState, UISideEffect>() {

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

    fun navigateTo(
        fragment: Fragment,
        backStack: String? = null,
        replace: Boolean = false,
        enterAnimation: Int = R.anim.slide_in_right,
        exitAnimation: Int = R.anim.slide_out_right,
        clearStack: Boolean = true,
        clearTop: Boolean = false
    ) {
        if (clearStack) clearBackStack(backStack) else if (clearTop) clearTop(fragment)
        if (replace)
            replaceFragment(R.id.fcv_fragment, fragment, enterAnimation, exitAnimation)
        else
            addFragment(R.id.fcv_fragment, fragment, enterAnimation, exitAnimation, backStack)
    }
}