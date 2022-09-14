package me.d3s34.ntlm

import me.d3s34.ntlm.utils.toByteArray

fun main(args: Array<String>) {
    val negotiateMessageParser = NegotiateMessageParser()
    val negotiateString = "TlRMTVNTUAABAAAAMbCI4h4AHgAoAAAABAAEAEYAAAAGAbEdAAAAD2V4Y2hhbmdlc2VydmVyLnZjc2xhYmt2bS5sb2NhbEJJRU4="

    println(negotiateMessageParser.parseFromBase64(negotiateString))
}
