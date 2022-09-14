package me.d3s34.ntlm.model

data class NegotiateMessage(
    override val signature: ByteArray,
    override val messageType: Int,
    val negotiateFlags: Long,
    val domainNameField: DomainNameField,
    val workstationField: WorkstationField,
    val version: Long,
    val payload: NegotiatePayload
): NtlmMessage()
