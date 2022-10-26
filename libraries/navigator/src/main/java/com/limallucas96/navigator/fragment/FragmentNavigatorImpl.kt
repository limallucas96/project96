package com.limallucas96.navigator.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.limallucas96.navigator.extensions.addFragment
import com.limallucas96.navigator.extensions.clearBackStack
import com.limallucas96.navigator.extensions.clearTop
import com.limallucas96.navigator.extensions.replaceFragment
import javax.inject.Inject
import com.limallucas96.uikit.R

internal class FragmentNavigatorImpl @Inject constructor() : FragmentNavigator {

    override fun navigateTo(
        fragmentActivity: FragmentActivity?,
        fragment: Fragment,
        backStack: String?,
        replace: Boolean,
        enterAnimation: Int,
        exitAnimation: Int,
        clearStack: Boolean,
        clearTop: Boolean
    ) {
        if (clearStack) fragmentActivity?.clearBackStack(backStack) else if (clearTop) fragmentActivity?.clearTop(fragment)
        if (replace)
            fragmentActivity?.replaceFragment(R.id.fcv_fragment, fragment, enterAnimation, exitAnimation)
        else
            fragmentActivity?.addFragment(R.id.fcv_fragment, fragment, enterAnimation, exitAnimation, backStack)
    }
}