package me.d3s34.ntlm.model

data class TargetInfoFields(
    val targetInfoLen: UShort,
    val targetInfoLenMax: UShort,
    val targetInfoBufferOffset: UInt
)