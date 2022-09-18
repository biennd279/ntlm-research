package me.d3s34.ntlm

import me.d3s34.ntlm.parser.ChallengeMessageParser
import me.d3s34.ntlm.parser.NegotiateMessageParser
import me.d3s34.ntlm.utils.toByteArray

fun main(args: Array<String>) {
    val negotiateMessageParser = NegotiateMessageParser()
    val negotiateString = "TlRMTVNTUAABAAAAMbCI4h4AHgAoAAAABAAEAEYAAAAGAbEdAAAAD2V4Y2hhbmdlc2VydmVyLnZjc2xhYmt2bS5sb2NhbEJJRU4="

    val challengeMessageParser = ChallengeMessageParser()
    val challengeString = "TlRMTVNTUAACAAAAEgASADgAAAA1goniEWRtyhEFqC8AAAAAAAAAAMoAygBKAAAACgA5OAAAAA9WAEMAUwBMAEEAQgBLAFYATQACABIAVgBDAFMATABBAEIASwBWAE0AAQAcAEUAWABDAEgAQQBOAEcARQBTAEUAUgBWAEUAUgAEAB4AVgBDAFMATABBAEIASwBWAE0ALgBsAG8AYwBhAGwAAwA8AEUAeABjAGgAYQBuAGcAZQBTAGUAcgB2AGUAcgAuAFYAQwBTAEwAQQBCAEsAVgBNAC4AbABvAGMAYQBsAAUAHgBWAEMAUwBMAEEAQgBLAFYATQAuAGwAbwBjAGEAbAAHAAgAxxwK5BbI2AEAAAAA"

    println(negotiateMessageParser.parseFromBase64(negotiateString))
    println(challengeMessageParser.parseFromBase64(challengeString))
}
