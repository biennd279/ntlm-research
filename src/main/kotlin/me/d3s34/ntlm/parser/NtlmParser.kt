package me.d3s34.ntlm.parser

import me.d3s34.ntlm.model.DomainNameFields
import me.d3s34.ntlm.model.NtlmMessage
import me.d3s34.ntlm.model.WorkstationFields
import me.d3s34.ntlm.utils.*
import java.util.Base64

abstract class NtlmParser() {
    abstract fun parse(dataBuffer: InputDataBuffer): NtlmMessage
    fun parse(byteArray: ByteArray) = parse(InputDataBuffer(byteArray))
    fun parseFromBase64(string: String) = parse(Base64.getDecoder().decode(string))

    enum class MessageType(
        val value: Int
    ) {
        NEGOTIATE(1), CHALLENGE(2), AUTHENTICATED(3)
    }

    fun parseDomainFields(domainFields: ByteArray): DomainNameFields {
        require(domainFields.size == 8)
        val data = InputDataBuffer(domainFields)
        return DomainNameFields(
            domainNameLen = data.takeNext(2).toShort(littleEndian = true).toUShort(),
            domainNameMaxLen = data.takeNext(2).toShort(littleEndian = true).toUShort(),
            domainNameBufferOffset = data.takeNext(4).toInt(littleEndian = true).toUInt()
        )
    }

    fun parseWorkstationFields(workstationFields: ByteArray): WorkstationFields {
        require(workstationFields.size == 8)
        val data = InputDataBuffer(workstationFields)

        return WorkstationFields(
            workstationLen = data.takeNext(2).toShort(littleEndian = true).toUShort(),
            workstationMaxLen = data.takeNext(2).toShort(littleEndian = true).toUShort(),
            workstationBufferOffset = data.takeNext(4).toInt(littleEndian = true).toUInt()
        )
    }

    protected fun hasVersion(flags: Int): Boolean {
        return flags and 1.shl(31 - 6) != 0
    }
}