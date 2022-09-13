package me.d3s34.ntlm.utils

interface DataBuffer {
    fun toByteArray(): ByteArray
}

class InputDataBuffer(
    private val byteArray: ByteArray
): DataBuffer {
    override fun toByteArray() = byteArray

    var index = 0
        private set

    fun peek(): Byte = byteArray.getOrNull(index) ?: throw Exception("End of stream")
    fun peekSafely(): Byte? = byteArray.getOrNull(index)

    fun requireNextByte(): Byte = this.nextByteOrNull() ?: throw Exception("End of stream")
    fun nextByteOrNull(): Byte? = byteArray.getOrNull(index)?.also { index++ }

    fun takeNext(next: Int): ByteArray {
        require(next > 0) { "Number of bytes to take must be greater than 0!" }

        return byteArray.copyOfRange(index, index + next).also { index += next}
    }
}

class OutputDataBuffer: DataBuffer {
    private val bytes = mutableListOf<Byte>()

    fun add(byte: Byte) = bytes.add(byte)
    fun addAll(bytes: List<Byte>) = this.bytes.addAll(bytes)
    fun addAll(bytes: ByteArray) = this.bytes.addAll(bytes.toList())

    override fun toByteArray() = bytes.toByteArray()
}