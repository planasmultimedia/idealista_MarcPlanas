package com.example.idealista_marcplanas.utils

object StringUtils {
    fun capitalizeFirstLetter(string: String): String {
        return string.lowercase().replaceFirstChar { it.uppercase() }
    }
}