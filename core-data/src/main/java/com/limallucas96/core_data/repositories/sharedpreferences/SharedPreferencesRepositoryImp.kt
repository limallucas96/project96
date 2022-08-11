package com.limallucas96.core_data.repositories.sharedpreferences

import android.content.Context
import com.limallucas96.core_data.datasource.SharedPreferenceDataSource
import javax.inject.Inject

class SharedPreferencesRepositoryImp @Inject constructor(context: Context) :
    SharedPreferenceDataSource {

    private val sharedPreferences by lazy {
        context.getSharedPreferences("${context.packageName}_preferences", Context.MODE_PRIVATE)
    }

    override fun getInt(key: String, default: Int): Int = sharedPreferences.getInt(key, default)

    override fun getString(key: String, default: String?): String? =
        sharedPreferences.getString(key, default)

    override fun getBoolean(key: String, default: Boolean) =
        sharedPreferences.getBoolean(key, default)

    override fun getLong(key: String, default: Long): Long = sharedPreferences.getLong(key, default)

    override fun putInt(key: String, value: Int) {
        sharedPreferences.edit().apply {
            putInt(key, value)
            apply()
        }
    }

    override fun putString(key: String, value: String) {
        sharedPreferences.edit().apply {
            putString(key, value)
            apply()
        }
    }

    override fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().apply {
            putBoolean(key, value)
            apply()
        }
    }

    override fun putLong(key: String, value: Long) {
        sharedPreferences.edit().apply {
            putLong(key, value)
            apply()
        }
    }

}