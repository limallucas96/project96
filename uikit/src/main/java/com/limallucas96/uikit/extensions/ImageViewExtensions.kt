package com.limallucas96.uikit.extensions

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadUrl(context: Context, url: String) {
    url.takeIf { it.isNotEmpty() }?.let { url ->
        Glide.with(context)
            .load(url)
            .into(this)
    }
}