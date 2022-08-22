package com.limallucas96.core_presentation.extensions

import androidx.fragment.app.FragmentManager

fun FragmentManager.runPendingTransactions() {
    try {
        this.executePendingTransactions()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}