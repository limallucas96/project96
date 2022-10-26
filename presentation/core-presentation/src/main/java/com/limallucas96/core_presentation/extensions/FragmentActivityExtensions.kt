package com.limallucas96.core_presentation.extensions

import androidx.fragment.app.FragmentActivity

fun FragmentActivity.isLastFragment(): Boolean {
    return supportFragmentManager.backStackEntryCount == 1
}

fun FragmentActivity.removeAllFragmentsFromBackStack() {
    try {
        for (i in supportFragmentManager.backStackEntryCount downTo 0) {
            supportFragmentManager.popBackStackImmediate()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}