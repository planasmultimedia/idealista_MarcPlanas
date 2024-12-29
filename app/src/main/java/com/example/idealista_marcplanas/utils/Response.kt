package com.example.idealista_marcplanas.utils

sealed class Response<out T> {
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val message: String, val cause: Throwable? = null) : Response<Nothing>()
    object Loading : Response<Nothing>()
}