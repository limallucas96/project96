package com.limallucas96.navigator.extensions

import android.app.Activity
import android.content.Intent

fun Activity.redirectToActivity(intent: Intent, clearTop: Boolean = false, singleTop: Boolean = false, finish: Boolean = false) {
    if (clearTop) this.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    if (singleTop) this.intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
    this.startActivity(intent)

    if (finish) this.finish()
}