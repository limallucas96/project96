package com.example.analytics.analytics

import android.util.Log
import javax.inject.Inject

class AnalyticsImpl @Inject constructor() : Analytics {

    override fun logFirebaseEvent(event: String) {
        // Fake log firebase event
        Log.d("MVI96Analytics FBEvent", event)

    }

    override fun logSomeOtherFrameWorkEvent(event: String) {
        // Fake log some other frame work event
        Log.d("MVI96Analytics FWEvent", event)
    }
}