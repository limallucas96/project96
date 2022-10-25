package com.limallucas96.core_presentation.resourceprovider

import android.content.Context
import androidx.annotation.*
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourcesProvider @Inject constructor(@ApplicationContext val context: Context) {

    fun getString(@StringRes resourceId: Int): String {
        return context.getString(resourceId)
    }

    fun getString(@StringRes resourceId: Int, vararg args: Any?): String {
        return context.getString(resourceId, *args)
    }

    fun getQuantityString(@PluralsRes resourceId: Int, quantity: Int): String {
        return context.resources.getQuantityString(resourceId, quantity)
    }

    fun getQuantityString(@PluralsRes resourceId: Int, quantity: Int, vararg args: Any?): String {
        return context.resources.getQuantityString(resourceId, quantity, *args)
    }

    fun getStringArray(@ArrayRes resourceId: Int): Array<String> {
        return context.resources.getStringArray(resourceId)
    }

    @ColorInt
    fun getColor(@ColorRes resourceId: Int): Int {
        return context.getColor(resourceId)
    }
}