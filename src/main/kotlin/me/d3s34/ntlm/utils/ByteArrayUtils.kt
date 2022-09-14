package me.d3s34.ntlm.utils

import java.nio.ByteBuffer
import java.nio.ByteOrder

fun Short.toByteArray(littleEndian: Boolean = false) =
    ByteArray(2) { ((this.toInt() shr (1 - it) * 8) and 0xff).toByte() }
        .let { if (littleEndian) it.reversedArray() else it }

fun Int.toByteArray(littleEndian: Boolean = false) = ByteArray(4) { ((this shr (3 - it) * 8) and 0xff).toByte() }
    .let { if (littleEndian) it.reversedArray() else it }

fun Long.toByteArray(littleEndian: Boolean = false) = ByteArray(8) { ((this shr (7 - it) * 8) and 0xff).toByte() }
    .let { if (littleEndian) it.reversedArray() else it }

//TODO: remake to get performance
fun ByteArray.pad(size: Int): ByteArray {
    if (this.size == size)
        return this

    return ByteArray(size - this.size) { 0 } + this
}

fun ByteArray.toByte(): Byte {
    return first()
}

fun ByteArray.toShort(littleEndian: Boolean = false): Short {
    return ByteBuffer.wrap(pad(2)).order(if (littleEndian) ByteOrder.LITTLE_ENDIAN else ByteOrder.BIG_ENDIAN).short
}

fun ByteArray.toInt(littleEndian: Boolean = false): Int {
    return ByteBuffer.wrap(pad(4)).order(if (littleEndian) ByteOrder.LITTLE_ENDIAN else ByteOrder.BIG_ENDIAN).int
}

fun ByteArray.toLong(littleEndian: Boolean = false): Long {
    return ByteBuffer.wrap(pad(8)).order(if (littleEndian) ByteOrder.LITTLE_ENDIAN else ByteOrder.BIG_ENDIAN).long
}

fun ByteArray.toFloat(littleEndian: Boolean = false): Float {
    require(size == 4)
    return ByteBuffer.wrap(this).order(if (littleEndian) ByteOrder.LITTLE_ENDIAN else ByteOrder.BIG_ENDIAN).float
}

fun ByteArray.toDouble(littleEndian: Boolean = false): Double {
    require(size == 8)
    return ByteBuffer.wrap(this).order(if (littleEndian) ByteOrder.LITTLE_ENDIAN else ByteOrder.BIG_ENDIAN).double
}

fun ByteArray.decodeHex() = this.joinToString(separator = "").decodeHex()
fun Byte.decodeHex() = this.toInt().and(0xff).toString(16).padStart(2, '0')

fun String.decodeHex(): ByteArray {
    require(length % 2 == 0)

    return this
        .chunked(2)
        .map { it.toByte(16) }
        .toByteArray()
}