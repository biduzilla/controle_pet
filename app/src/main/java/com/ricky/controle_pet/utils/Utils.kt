package com.ricky.controle_pet.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale

fun bitmapToByteArray(
    bitmap: Bitmap,
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
    maxFileSizeInBytes: Int = 2 * 1024 * 1024,
    maxWidth: Int = 800,
    maxHeight: Int = 800
): ByteArray {
    val resizedBitmap = resizeBitmap(bitmap, maxWidth, maxHeight)

    var quality = 100
    var byteArray: ByteArray

    do {
        val stream = ByteArrayOutputStream()
        resizedBitmap.compress(format, quality, stream)
        byteArray = stream.toByteArray()
        quality -= 5  // Reduz a qualidade em passos de 5
    } while (byteArray.size > maxFileSizeInBytes && quality > 0)

    return byteArray
}

fun resizeBitmap(bitmap: Bitmap, maxWidth: Int, maxHeight: Int): Bitmap {
    val width = bitmap.width
    val height = bitmap.height
    val aspectRatio = width.toFloat() / height.toFloat()

    var newWidth = maxWidth
    var newHeight = maxHeight

    if (width > height) {
        newHeight = (newWidth / aspectRatio).toInt()
    } else if (height > width) {
        newWidth = (newHeight * aspectRatio).toInt()
    } else {
        newWidth = maxWidth
        newHeight = maxHeight
    }

    return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
}

fun Context.getTempUri(): Uri? {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale("pt", "BR"))
    val imageFileName = "JPEG_" + timeStamp + "_"
    val file = File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )
    return FileProvider.getUriForFile(
        this,
        "com.ricky.controle_pet.components.fileprovider",
        file
    )
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

fun LocalDate.dataParaLongEspeficica(dia: Int, hora: Int): Long {
    val localDate = this.atTime(hora, 0).plusDays(dia.toLong())
    return localDate.toInstant(ZoneOffset.UTC).toEpochMilli()
}
