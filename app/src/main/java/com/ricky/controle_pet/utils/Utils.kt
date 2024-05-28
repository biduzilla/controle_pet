package com.ricky.controle_pet.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.ByteArrayOutputStream
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.Locale

fun bitmapToByteArray(
    bitmap: Bitmap,
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG,
    quality: Int = 100
): ByteArray {
    val stream = ByteArrayOutputStream()
    bitmap.compress(format, quality, stream)
    return stream.toByteArray()
}

fun uriToBitmap(context: Context, uri: Uri): Bitmap? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream?.close()
        bitmap
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}

fun formatterLocalDate(localDate: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern(
        "dd/MMMM/yyyy",
        Locale("pt", "BR")
    )
    return localDate.format(formatter)
}

fun calculateAgeAndMonths(birthDate: LocalDate): String {
    val currentDate = LocalDate.now()
    val period = Period.between(birthDate, currentDate)

    val years = period.years
    val months = period.months
    val days = period.days

    return when {
        years == 0 && months == 0 -> "$days dias"
        years == 0 -> "$months meses e $days dias"
        else -> "$years anos e $months meses"
    }
}
