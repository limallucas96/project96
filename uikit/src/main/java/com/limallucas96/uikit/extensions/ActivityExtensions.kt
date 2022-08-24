package com.limallucas96.uikit.extensions

import android.app.Activity

inline fun <reified T> Activity.extra(key: String, crossinline default: () -> T): Lazy<T> = lazy {
    val value = intent.extras?.get(key)
    if (value is T) value else default()
}