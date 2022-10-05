package com.example.food2forkkmm.domain.util

data class DataState<T>(
    val message: String? = null,
    val data: T? = null,
    val isLoading: Boolean = false
) {
    companion object {
        fun <T> error(
            message: String
        ): DataState<T> = DataState(message = message)

        fun <T> data(
            message: String?,
            data: T? = null
        ): DataState<T> = DataState(message = message, data = data)

        fun <T> loading(): DataState<T> = DataState(isLoading = true)
    }
}