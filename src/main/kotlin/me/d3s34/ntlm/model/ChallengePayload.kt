package me.d3s34.ntlm.model

data class ChallengePayload(
    val targetName: String,
    val targetInfo: ByteArray
)