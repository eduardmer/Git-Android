package com.core_model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface Result {
    data class Success<T>(val data: T) : Result
    data class Error(val error: Throwable) : Result
    object Loading : Result
}

fun <T> Flow<T>.asResult(): Flow<Result> {
    return map<T, Result> {
        Result.Success(it)
    }.onStart {
        emit(Result.Loading)
    }.catch {
        it.printStackTrace()
        emit(Result.Error(it))
    }
}