package com.limallucas96.uikit.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment

inline fun <reified T> Fragment.argument(key: String, crossinline default: () -> T): Lazy<T> = lazy {
    val value = arguments?.get(key)
    if (value is T) value else default()
}

inline fun <T : Fragment> T.withArgs(argsBuilder: Bundle.() -> Unit): T = this.apply {
    arguments = Bundle().apply(argsBuilder)
}