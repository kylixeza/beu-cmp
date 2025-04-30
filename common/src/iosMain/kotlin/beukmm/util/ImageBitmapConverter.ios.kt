package beukmm.util

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.EncodedImageFormat
import org.jetbrains.skia.Image
import org.jetbrains.skia.Surface

actual fun ImageBitmap.toJPGByteArray(): ByteArray {
    val skiaBitmap = this.asSkiaBitmap()
    return skiaBitmap.bestOptimizationImageQuality(EncodedImageFormat.JPEG)
}

actual fun ByteArray.toImageBitmap(): ImageBitmap {
    return Image.makeFromEncoded(this).toComposeImageBitmap()
}

/**
 * Convert ImageBitmap to ByteArray with best optimization quality so it can be saved in database
 * with size less than 150kb
 */

fun Bitmap.bestOptimizationImageQuality(
    format: EncodedImageFormat
): ByteArray {

    val earlyByteArray = Image.makeFromBitmap(this).encodeToData(format, 50)?.bytes ?: byteArrayOf()
    if (earlyByteArray.size < 150 * 1024) {
        return earlyByteArray
    }

    var quality = 100
    var byteArray: ByteArray

    val resizedBitmap = this.scale(0.5f)

    do {
        byteArray = Image.makeFromBitmap(resizedBitmap).encodeToData(format, quality)?.bytes ?: byteArrayOf()
        if (byteArray.size < 150 * 1024) break
        quality -= 10
    } while (quality > 0)

    return byteArray
}

fun Bitmap.scale(factor: Float): Bitmap {
    val newWidth = (this.width * factor).toInt()
    val newHeight = (this.height * factor).toInt()
    val surface = Surface.makeRasterN32Premul(newWidth, newHeight)
    val canvas = surface.canvas
    canvas.drawImage(
        Image.makeFromBitmap(this),
        0f,
        0f,
        null
    )
    return surface.makeImageSnapshot().toComposeImageBitmap().asSkiaBitmap()
}