package com.limallucas96.navigator.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.limallucas96.uikit.R

interface FragmentNavigator {

    fun navigateTo(
        fragmentActivity: FragmentActivity?,
        fragment: Fragment,
        backStack: String? = null,
        replace: Boolean = false,
        enterAnimation: Int = R.anim.slide_in_right,
        exitAnimation: Int = R.anim.slide_out_right,
        clearStack: Boolean = true,
        clearTop: Boolean = false
    )
}