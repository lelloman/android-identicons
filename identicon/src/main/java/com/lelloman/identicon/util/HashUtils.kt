package com.lelloman.identicon.util

import java.security.MessageDigest

fun md5(input: ByteArray): ByteArray =
    MessageDigest.getInstance("MD5").digest(input)

/**
 * Extract [count] bits starting at [offset] from [md5] byte array.
 * Bits are numbered from MSB of byte 0. Returns an unsigned Int.
 */
fun extractBits(md5: ByteArray, offset: Int, count: Int): Int {
    var result = 0
    for (i in 0 until count) {
        val bitIndex = offset + i
        val byteIndex = bitIndex / 8
        val bitPosition = 7 - (bitIndex % 8)
        val bit = (md5[byteIndex].toInt() shr bitPosition) and 1
        result = (result shl 1) or bit
    }
    return result
}

fun Int.toIdenticonHash(): ByteArray {
    val bytes = byteArrayOf(
        (this shr 24).toByte(),
        (this shr 16).toByte(),
        (this shr 8).toByte(),
        this.toByte()
    )
    return md5(bytes)
}

/**
 * Converts an Int to a 16-byte array without MD5, mapping int bit N to
 * extractBits position N. This means the low bits of the int (which change
 * fastest when incrementing) control shape fields (positions 0-18), while
 * high bits control color (positions 19+). Useful for demo sequences where
 * shapes should cycle before colors change.
 */
fun Int.toSequentialBytes(): ByteArray {
    val bytes = ByteArray(16)
    for (i in 0 until 32) {
        val bit = (this shr i) and 1
        val byteIndex = i / 8
        val bitPosition = 7 - (i % 8)
        bytes[byteIndex] = (bytes[byteIndex].toInt() or (bit shl bitPosition)).toByte()
    }
    return bytes
}
