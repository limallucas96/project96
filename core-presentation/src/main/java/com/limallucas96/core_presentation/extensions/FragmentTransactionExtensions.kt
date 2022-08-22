package com.limallucas96.core_presentation.extensions

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentTransaction?.commitSafe(fragmentManager: FragmentManager?) {
    fragmentManager?.let { fm ->
        if (!fm.isDestroyed && !fm.isStateSaved)
            this?.commit()
        else
            this?.commitAllowingStateLoss()
    }
}


fun FragmentTransaction?.commitAllowingStateLoss(fragmentManager: FragmentManager?) {
    fragmentManager?.let {
        this?.commitAllowingStateLoss()
    }
}