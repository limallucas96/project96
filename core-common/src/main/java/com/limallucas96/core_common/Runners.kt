package com.limallucas96.core_common.runners

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> runCatchingIO(func: suspend () -> T): Result<T> =
    withContext(Dispatchers.IO) {
        runCatching {
            func()
        }.onSuccess {
            Result.success(it)
        }.onFailure {
            Result.failure<Throwable>(it)
        }
    }