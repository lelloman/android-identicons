package com.lelloman.identicon

import com.lelloman.identicon.util.extractBits
import com.lelloman.identicon.util.md5
import com.lelloman.identicon.util.toIdenticonHash
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

class HashUtilsTest {

    @Test
    fun `md5 is deterministic`() {
        val input = byteArrayOf(1, 2, 3, 4)
        assertArrayEquals(md5(input), md5(input))
    }

    @Test
    fun `md5 returns 16 bytes`() {
        assertEquals(16, md5(byteArrayOf(0)).size)
        assertEquals(16, md5(byteArrayOf()).size)
        assertEquals(16, md5(byteArrayOf(1, 2, 3, 4, 5)).size)
    }

    @Test
    fun `different inputs produce different md5`() {
        val a = md5(byteArrayOf(1))
        val b = md5(byteArrayOf(2))
        assert(!a.contentEquals(b))
    }

    @Test
    fun `extractBits single bit from first byte`() {
        // 0b10110000 = 0xB0
        val data = byteArrayOf(0xB0.toByte())
        assertEquals(1, extractBits(data, 0, 1)) // bit 7 = 1
        assertEquals(0, extractBits(data, 1, 1)) // bit 6 = 0
        assertEquals(1, extractBits(data, 2, 1)) // bit 5 = 1
        assertEquals(1, extractBits(data, 3, 1)) // bit 4 = 1
    }

    @Test
    fun `extractBits multiple bits`() {
        // 0b11001010 = 0xCA
        val data = byteArrayOf(0xCA.toByte())
        assertEquals(0b1100, extractBits(data, 0, 4)) // first 4 bits
        assertEquals(0b1010, extractBits(data, 4, 4)) // last 4 bits
        assertEquals(0b110010, extractBits(data, 0, 6)) // first 6 bits
    }

    @Test
    fun `extractBits across byte boundary`() {
        // 0xFF 0x00
        val data = byteArrayOf(0xFF.toByte(), 0x00.toByte())
        assertEquals(0b1111_0000, extractBits(data, 4, 8)) // last 4 of byte 0 + first 4 of byte 1
    }

    @Test
    fun `extractBits returns values in expected range`() {
        val data = md5(byteArrayOf(42))
        for (offset in 0..120) {
            val value = extractBits(data, offset, 6)
            assert(value in 0..63) { "6-bit value $value at offset $offset out of range" }
        }
    }

    @Test
    fun `toIdenticonHash is deterministic`() {
        assertArrayEquals(42.toIdenticonHash(), 42.toIdenticonHash())
    }

    @Test
    fun `toIdenticonHash different ints produce different hashes`() {
        assert(!0.toIdenticonHash().contentEquals(1.toIdenticonHash()))
    }

    @Test
    fun `toIdenticonHash returns 16 bytes`() {
        assertEquals(16, 0.toIdenticonHash().size)
        assertEquals(16, Int.MAX_VALUE.toIdenticonHash().size)
        assertEquals(16, Int.MIN_VALUE.toIdenticonHash().size)
    }
}
