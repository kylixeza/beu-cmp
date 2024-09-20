package beukmm.util

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import org.jetbrains.skia.EncodedImageFormat
import org.jetbrains.skia.Image

actual fun ImageBitmap.toByteArray(): ByteArray {
    val skiaBitmap = this.asSkiaBitmap()
    return Image.makeFromBitmap(skiaBitmap).encodeToData(EncodedImageFormat.JPEG, 75)?.bytes ?: byteArrayOf()
}