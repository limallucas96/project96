package com.limallucas96.navigator.commons

import android.content.Context
import android.content.Intent

fun String.toIntent(context: Context) = Intent(this).setPackage(context.packageName)
