package me.d3s34.ntlm.model

data class NegotiateMessage(
    override val signature: ByteArray,
    override val messageType: Int,
    val negotiateFlags: Int,
    val domainNameFields: DomainNameFields,
    val workstationFields: WorkstationFields,
    val version: Long,
    val payload: NegotiatePayload
): NtlmMessage()
