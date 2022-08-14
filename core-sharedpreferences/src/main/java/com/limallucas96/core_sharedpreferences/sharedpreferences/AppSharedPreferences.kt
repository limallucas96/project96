package com.limallucas96.core_sharedpreferences.sharedpreferences

interface AppSharedPreferences {

    fun getInt(key: String, default: Int = -1): Int
    fun getString(key: String, default: String? = null): String?
    fun getBoolean(key: String, default: Boolean = false): Boolean
    fun getLong(key: String, default: Long = -1L): Long

    fun putInt(key: String, value: Int)
    fun putString(key: String, value: String)
    fun putBoolean(key: String, value: Boolean)
    fun putLong(key: String, value: Long)

}