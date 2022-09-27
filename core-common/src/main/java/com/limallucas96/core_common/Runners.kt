package com.limallucas96.core_common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

suspend fun <T> runSafeCall(dispatcher: CoroutineDispatcher, func: suspend () -> T): Result<T> =
    withContext(dispatcher) {
        runCatching {
            func()
        }.onSuccess {
            Result.success(it)
        }.onFailure {
            Result.failure<Throwable>(it)
        }
    }

suspend fun <T> runFlowableSafeCall(
    dispatcher: CoroutineDispatcher,
    func: suspend () -> T
): Flow<Result<T>> =
    flowOf(
        runCatching {
            func()
        }.onSuccess {
            Result.success(it)
        }.onFailure {
            Result.failure<Throwable>(it)
        }
    ).flowOn(dispatcher)