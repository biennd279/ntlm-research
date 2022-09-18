package me.d3s34.ntlm.parser

import me.d3s34.ntlm.model.NegotiateMessage
import me.d3s34.ntlm.model.NegotiatePayload
import me.d3s34.ntlm.model.NtlmMessage
import me.d3s34.ntlm.utils.InputDataBuffer
import me.d3s34.ntlm.utils.toInt
import me.d3s34.ntlm.utils.toLong
import java.nio.charset.Charset

class NegotiateMessageParser(
) : NtlmParser() {
    override fun parse(dataBuffer: InputDataBuffer): NtlmMessage {
        val signature = dataBuffer.takeNext(8)
        val messageType = dataBuffer.takeNext(4).toInt(littleEndian = true)

        require(messageType == MessageType.NEGOTIATE.value)

        val negotiateFlags = dataBuffer.takeNext(4).toInt(littleEndian = true)
        val domainNameFields = parseDomainFields(dataBuffer.takeNext(8))
        val workstationFields = parseWorkstationFields(dataBuffer.takeNext(8))
        val version = if (hasVersion(negotiateFlags)) dataBuffer.takeNext(8).toLong(littleEndian = true) else 0

        val domainName = if (domainNameFields.domainNameLen > 0u)
            dataBuffer.takeFromBegin(
                domainNameFields.domainNameBufferOffset.toInt(),
                domainNameFields.domainNameLen.toInt()
            )
                .toString(Charset.defaultCharset()) else ""

        val workstationName = if (workstationFields.workstationLen > 0u)
            dataBuffer.takeFromBegin(
                workstationFields.workstationBufferOffset.toInt(),
                workstationFields.workstationLen.toInt()
            )
                .toString(Charset.defaultCharset()) else ""

        return NegotiateMessage(
            signature = signature,
            messageType = messageType,
            negotiateFlags = negotiateFlags,
            domainNameFields = domainNameFields,
            workstationFields = workstationFields,
            version = version,
            payload = NegotiatePayload(
                domainName = domainName,
                workstationName = workstationName
            )
        )
    }
}