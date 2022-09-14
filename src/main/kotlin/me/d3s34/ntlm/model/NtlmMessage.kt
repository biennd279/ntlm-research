package me.d3s34.ntlm.model

abstract class NtlmMessage {
    abstract val signature: ByteArray
    abstract val messageType: Int
}