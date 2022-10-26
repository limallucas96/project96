package com.limallucas96.navigator.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentActivity.clearBackStack(backStack: String? = null) {
    supportFragmentManager.run {
        popBackStack(backStack, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        runPendingTransactions()
    }
}

fun FragmentActivity.clearTop(fragment: Fragment) {
    supportFragmentManager.run {
        fragments.firstOrNull { it.javaClass.name == fragment.javaClass.name }?.let {
            do {
                val lastFragment = fragments.last()
                popBackStack()
                runPendingTransactions()
            } while (lastFragment != it)
        }
    }
}

fun FragmentActivity.replaceFragment(
    containerViewId: Int,
    fragment: Fragment,
    enterAnimation: Int = FragmentTransaction.TRANSIT_NONE,
    exitAnimation: Int = FragmentTransaction.TRANSIT_NONE,
    backStack: String? = null,
    tag: String? = fragment.javaClass.canonicalName,
    allowStateLoss: Boolean = true
) {
    supportFragmentManager.beginTransaction().apply {
        setCustomAnimations(enterAnimation, exitAnimation)
        replace(containerViewId, fragment, tag)
        backStack?.let { addToBackStack(it) }
        if (allowStateLoss)
            commitAllowingStateLoss(supportFragmentManager)
        else
            commitSafe(supportFragmentManager)
    }
}

fun FragmentActivity.addFragment(
    containerViewId: Int,
    fragment: Fragment,
    enterAnimation: Int = FragmentTransaction.TRANSIT_NONE,
    exitAnimation: Int = FragmentTransaction.TRANSIT_NONE,
    backStack: String? = null,
    tag: String? = fragment.javaClass.canonicalName
) {
    if (!fragment.isAdded) {
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(enterAnimation, exitAnimation, enterAnimation, exitAnimation)
            add(containerViewId, fragment, tag)
            backStack?.let { addToBackStack(it) }
            commitSafe(supportFragmentManager)
        }
    }
}

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