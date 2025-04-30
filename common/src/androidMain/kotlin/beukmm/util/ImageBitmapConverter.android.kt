package beukmm.util

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.util.fastRoundToInt
import java.io.ByteArrayOutputStream

actual fun ImageBitmap.toJPGByteArray(): ByteArray {
    val androidBitmap = this.asAndroidBitmap()
    return androidBitmap.bestOptimizationImageQuality(CompressFormat.JPEG)
}

actual fun ByteArray.toImageBitmap(): ImageBitmap {
    return BitmapFactory.decodeByteArray(this, 0, size).asImageBitmap()
}

/**
 * Convert ImageBitmap to ByteArray with best optimization quality so it can be saved in database
 * with size less than 150kb
 */
private fun Bitmap.bestOptimizationImageQuality(
    format: CompressFormat
): ByteArray {

    val outputStream = ByteArrayOutputStream()
    this.compress(format, 50, outputStream)
    val earlyByteArray = outputStream.toByteArray()

    if (earlyByteArray.size < 150 * 1024) {
        return earlyByteArray
    }

    outputStream.reset()
    var quality = 100

    val resizedBitmap = Bitmap.createScaledBitmap(
        this,
        (this.width * 0.5).fastRoundToInt(),
        (this.height * 0.5).fastRoundToInt(),
        true
    )
    var byteArray: ByteArray

    do {
        outputStream.reset()
        resizedBitmap.compress(format, quality, outputStream)
        byteArray = outputStream.toByteArray()
        if (byteArray.size < 150 * 1024) break
        quality -= if (quality < 10) 2 else 10
    } while (quality > 0)

    return byteArray
}