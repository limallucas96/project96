package com.example.analytics.analytics

interface Analytics {
    fun logFirebaseEvent(event: String)
    fun logSomeOtherFrameWorkEvent(event: String)
}