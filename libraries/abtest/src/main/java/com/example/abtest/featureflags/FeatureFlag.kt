package com.example.abtest.featureflags

enum class FeatureFlag(
    override val key: String,
    override val title: String,
    override val explanation: String,
    override val defaultValue: Boolean = true
) : Feature {
    DARK_MODE("feature.darkmode", "Dark theme", "Enabled dark mode")
}