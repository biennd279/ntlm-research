package me.d3s34.ntlm.parser

import me.d3s34.ntlm.model.*
import me.d3s34.ntlm.utils.InputDataBuffer
import me.d3s34.ntlm.utils.toInt
import me.d3s34.ntlm.utils.toLong
import me.d3s34.ntlm.utils.toShort
import java.nio.charset.Charset

class ChallengeMessageParser : NtlmParser() {
    override fun parse(dataBuffer: InputDataBuffer): NtlmMessage {
        val signature = dataBuffer.takeNext(8)
        val messageType = dataBuffer.takeNext(4).toInt(littleEndian = true)
        val targetNameFields = TargetNameFields(
            targetNameLen = dataBuffer.takeNext(2).toShort(littleEndian = true).toUShort(),
            targetNameMaxLen = dataBuffer.takeNext(2).toShort(littleEndian = true).toUShort(),
            targetNameBufferOffset = dataBuffer.takeNext(4).toInt(littleEndian = true).toUInt()
        )
        val negotiateFlags = dataBuffer.takeNext(4).toInt(littleEndian = true)
        val serverChallenge = dataBuffer.takeNext(8)
        val reserved = dataBuffer.takeNext(8).toLong(littleEndian = true) // Reserved
        val targetInfoFields = TargetInfoFields(
            targetInfoLen = dataBuffer.takeNext(2).toShort(littleEndian = true).toUShort(),
            targetInfoLenMax = dataBuffer.takeNext(2).toShort(littleEndian = true).toUShort(),
            targetInfoBufferOffset = dataBuffer.takeNext(4).toInt(littleEndian = true).toUInt()
        )

        val version = if (hasVersion(negotiateFlags)) dataBuffer.takeNext(8).toLong() else 0

        val targetName = with(targetNameFields) {
            if (targetNameLen > 0u) dataBuffer.takeFromBegin(targetNameBufferOffset.toInt(), targetNameLen.toInt())
                .toString(Charsets.UTF_16LE) else ""
        }

        val targetInfo = with(targetInfoFields) {
            if (targetInfoLen > 0u) dataBuffer.takeFromBegin(
                targetInfoBufferOffset.toInt(),
                targetInfoLen.toInt()
            ) else byteArrayOf()
        }

        val payload = ChallengePayload(
            targetName = targetName,
            targetInfo = targetInfo
        )

        return ChallengeMessage(
            signature = signature,
            messageType = messageType,
            targetNameFields = targetNameFields,
            negotiateFlags = negotiateFlags,
            serverChallenge = serverChallenge,
            reserved = reserved,
            targetInfoFields = targetInfoFields,
            version = version,
            payload = payload
        )
    }
}