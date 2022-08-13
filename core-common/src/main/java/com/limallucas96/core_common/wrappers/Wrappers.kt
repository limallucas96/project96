package com.limallucas96.core_common.wrappers

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Error(val throwable: Exception) : ResultWrapper<Nothing>()
}