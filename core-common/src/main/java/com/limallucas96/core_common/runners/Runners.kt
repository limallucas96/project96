package com.limallucas96.core_common.runners

import com.limallucas96.core_common.wrappers.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> runIOSafeCall(func: suspend () -> T): ResultWrapper<T> =
    withContext(Dispatchers.IO) {
        try {
            val suspendFunc = func()
            ResultWrapper.Success(suspendFunc)
        } catch (error: Exception) {
            error.printStackTrace()
            ResultWrapper.Error(error)
        }
    }
