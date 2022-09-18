package me.d3s34.ntlm.model

data class DomainNameFields(
    val domainNameLen: UShort,
    val domainNameMaxLen: UShort,
    val domainNameBufferOffset: UInt,
)
