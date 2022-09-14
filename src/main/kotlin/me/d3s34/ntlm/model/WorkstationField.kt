package me.d3s34.ntlm.model

data class WorkstationField(
    val workstationLen: UShort,
    val workstationMaxLen: UShort,
    val workstationBufferOffset: UInt
)