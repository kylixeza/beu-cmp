package beukmm.util

import androidx.compose.ui.graphics.ImageBitmap

expect fun ImageBitmap.toJPGByteArray(): ByteArray

expect fun ByteArray.toImageBitmap(): ImageBitmap

fun ByteArray.compressForClassify() = this.toImageBitmap().toJPGByteArray()