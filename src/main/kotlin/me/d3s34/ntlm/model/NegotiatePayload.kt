package me.d3s34.ntlm.model

data class NegotiatePayload(
    val domainName: String,
    val workstationName: String
)
