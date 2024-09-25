package com.kylix.camera.tflite

expect class TFLiteHelper {
    fun classifyImage(byteArray: ByteArray): String
}