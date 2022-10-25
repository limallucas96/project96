package com.limallucas96.navigator.navigator

import android.content.Context
import android.content.Intent

interface Navigator {
    fun newIntent(context: Context): Intent
}