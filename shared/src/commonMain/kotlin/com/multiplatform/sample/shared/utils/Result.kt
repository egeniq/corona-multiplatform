package com.multiplatform.sample.shared.utils


/**
 * Class combines state and data in one object.
 * Does pretty much the same as kotlin.Result, but also has additional {@code InProgress} state
 */
sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val exception: Throwable, val message: String? = exception.message) : Result<Nothing>()
    object InProgress : Result<Nothing>()
    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Error[exception=$exception]"
            InProgress -> "Loading"
        }
    }

    fun inProgress() = this is InProgress

    companion object{
        fun <T> success(data: T): Result<T> = Success(data)
        fun <T> failure(e: Throwable) : Result<T> = Failure(exception = e)
        fun <T> failure(e: Throwable, message: String) : Result<T> = Failure(exception = e, message = message)
        fun <T> inProgress(): Result<T> = InProgress
    }

}

fun <T> Result<T>.resolve(onSuccess: (T) -> Unit = {}, onError: (E: Throwable) -> Unit = {}) {
    when (this) {
        is Result.Success -> {
            onSuccess(data)
        }
        is Result.Failure -> {
            onError(exception)
        }
    }
}
