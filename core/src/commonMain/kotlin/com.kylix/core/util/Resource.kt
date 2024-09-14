package com.kylix.core.util

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.fold

data class Success<T>(val data: T)
data class Error(val message: String)

fun<T> Result<Success<T>, Error>.foldResult(
    onSuccess: (data: T) -> Unit,
    onError: (message: String) -> Unit
) {
    this.fold(
        { onSuccess(it.data) },
        { onError(it.message) }
    )
}
