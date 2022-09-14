package me.d3s34.ntlm.model

data class DomainNameField(
    val domainNameLen: UShort,
    val domainNameMaxLen: UShort,
    val domainNameBufferOffset: UInt,
)
