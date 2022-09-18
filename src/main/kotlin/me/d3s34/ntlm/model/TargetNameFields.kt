package me.d3s34.ntlm.model

data class TargetNameFields(
    val targetNameLen: UShort,
    val targetNameMaxLen: UShort,
    val targetNameBufferOffset: UInt,
)
