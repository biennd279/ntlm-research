package me.d3s34.ntlm.model

data class ChallengeMessage(
    override val signature: ByteArray,
    override val messageType: Int,
    val targetNameFields: TargetNameFields,
    val negotiateFlags: Int,
    val serverChallenge: ByteArray,
    val reserved: Long,
    val targetInfoFields: TargetInfoFields,
    val version: Long,
    val payload: ChallengePayload,
): NtlmMessage()
