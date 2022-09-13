package me.d3s34.ntlm

import me.d3s34.ntlm.utils.InputDataBuffer

fun main(args: Array<String>) {
    val byteArray = byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
    val inputDataBuffer = InputDataBuffer(byteArray)

    println(inputDataBuffer.peek())
    println(inputDataBuffer.nextByteOrNull())
    println(inputDataBuffer.takeNext(3).toList())
    println(inputDataBuffer.nextByteOrNull())
    println(inputDataBuffer.takeNext(3).toList())
}