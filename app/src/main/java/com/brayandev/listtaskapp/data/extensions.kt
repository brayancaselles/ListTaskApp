package com.brayandev.listtaskapp.data

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import java.io.IOException
import com.brayandev.listtaskapp.domain.model.Error

fun Throwable.toError(): Error = when (this) {
    is IOException -> Error.Connectivity
    else -> Error.Unknown(message ?: "")
}

suspend fun <T> tryCall(action: suspend () -> T): Either<Error, T> = try {
    action().right()
} catch (e: Exception) {
    e.toError().left()
}
