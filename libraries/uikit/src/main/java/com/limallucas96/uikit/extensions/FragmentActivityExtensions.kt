package com.limallucas96.uikit.extensions

import android.app.AlertDialog
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.showAppDialog(
    title: String,
    body: String,
    positiveText: String,
    negativeText: String? = null,
    neutralText: String? = null,
    positiveAction: (() -> Unit)? = null,
    negativeAction: (() -> Unit)? = null,
    neutralAction: (() -> Unit)? = null,
    isCancelable: Boolean = true
) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(body)
        .setCancelable(isCancelable)
        .setPositiveButton(positiveText) { dialog, _ ->
            dialog.dismiss()
            positiveAction?.invoke()
        }
        .setNegativeButton(negativeText) { dialog, _ ->
            dialog.dismiss()
            negativeAction?.invoke()
        }
        .setNegativeButton(neutralText) { dialog, _ ->
            dialog.dismiss()
            neutralAction?.invoke()
        }
        .show()
}