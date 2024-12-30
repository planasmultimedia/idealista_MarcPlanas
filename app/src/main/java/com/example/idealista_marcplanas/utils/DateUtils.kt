package com.example.idealista_marcplanas.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {
    fun formatLongToReadableDate(timeInMillis: Long): String {
        val sdf = SimpleDateFormat("dd/MM/YY", Locale.getDefault())
        return sdf.format(Date(timeInMillis))
    }
}