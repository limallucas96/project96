package com.example.abtest.featureflags

enum class TestSetting(
    override val key: String,
    override val title: String,
    override val explanation: String,
    override val defaultValue: Boolean = false
) : Feature {
    USE_DEVELOP_PORTAL("testsetting.usedevelopportal", "Development portal", "Use developer REST endpoint"),
    DEBUG_LOGGING("testsetting.debuglogging", "Enable logging", "Print all app logging to console", defaultValue = true)
}