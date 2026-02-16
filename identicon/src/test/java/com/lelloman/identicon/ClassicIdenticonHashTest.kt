package com.lelloman.identicon

import com.lelloman.identicon.drawable.ClassicIdenticonHash
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ClassicIdenticonHashTest {

    @Test
    fun `middle is multiple of 4 and in range 0-28`() {
        for (i in listOf(0, 1, -1, 42, 255, Int.MIN_VALUE, Int.MAX_VALUE)) {
            val hash = ClassicIdenticonHash(i)
            assertTrue("middle=${hash.middle} for input=$i", hash.middle in 0..28)
            assertEquals("middle should be multiple of 4", 0, hash.middle % 4)
        }
    }

    @Test
    fun `corners in range 0-31`() {
        for (i in listOf(0, 1, -1, 42, 255, Int.MIN_VALUE, Int.MAX_VALUE)) {
            val hash = ClassicIdenticonHash(i)
            assertTrue("corners=${hash.corners} for input=$i", hash.corners in 0..31)
        }
    }

    @Test
    fun `sides in range 0-31`() {
        for (i in listOf(0, 1, -1, 42, 255, Int.MIN_VALUE, Int.MAX_VALUE)) {
            val hash = ClassicIdenticonHash(i)
            assertTrue("sides=${hash.sides} for input=$i", hash.sides in 0..31)
        }
    }

    @Test
    fun `invert flags are 0 or 1`() {
        for (i in listOf(0, 1, -1, 42, 255, Int.MIN_VALUE, Int.MAX_VALUE)) {
            val hash = ClassicIdenticonHash(i)
            assertTrue("invertMiddle=${hash.invertMiddle}", hash.invertMiddle in 0..1)
            assertTrue("invertCorners=${hash.invertCorners}", hash.invertCorners in 0..1)
            assertTrue("invertSides=${hash.invertSides}", hash.invertSides in 0..1)
        }
    }

    @Test
    fun `rgb values in range 0-15`() {
        for (i in listOf(0, 1, -1, 42, 255, Int.MIN_VALUE, Int.MAX_VALUE)) {
            val hash = ClassicIdenticonHash(i)
            assertTrue("r=${hash.r}", hash.r in 0..15)
            assertTrue("g=${hash.g}", hash.g in 0..15)
            assertTrue("b=${hash.b}", hash.b in 0..15)
        }
    }

    @Test
    fun `rotation values in range 0-3`() {
        for (i in listOf(0, 1, -1, 42, 255, Int.MIN_VALUE, Int.MAX_VALUE)) {
            val hash = ClassicIdenticonHash(i)
            assertTrue("cornersRotation=${hash.cornersRotation}", hash.cornersRotation in 0..3)
            assertTrue("sidesRotation=${hash.sidesRotation}", hash.sidesRotation in 0..3)
        }
    }

    @Test
    fun `hash 0 produces all zeros`() {
        val hash = ClassicIdenticonHash(0)
        assertEquals(0, hash.middle)
        assertEquals(0, hash.corners)
        assertEquals(0, hash.sides)
        assertEquals(0, hash.invertMiddle)
        assertEquals(0, hash.invertCorners)
        assertEquals(0, hash.invertSides)
        assertEquals(0, hash.r)
        assertEquals(0, hash.g)
        assertEquals(0, hash.b)
        assertEquals(0, hash.cornersRotation)
        assertEquals(0, hash.sidesRotation)
    }

    @Test
    fun `same hash produces same output`() {
        val hash1 = ClassicIdenticonHash(12345)
        val hash2 = ClassicIdenticonHash(12345)
        assertEquals(hash1.middle, hash2.middle)
        assertEquals(hash1.corners, hash2.corners)
        assertEquals(hash1.sides, hash2.sides)
        assertEquals(hash1.invertMiddle, hash2.invertMiddle)
        assertEquals(hash1.invertCorners, hash2.invertCorners)
        assertEquals(hash1.invertSides, hash2.invertSides)
        assertEquals(hash1.r, hash2.r)
        assertEquals(hash1.g, hash2.g)
        assertEquals(hash1.b, hash2.b)
        assertEquals(hash1.cornersRotation, hash2.cornersRotation)
        assertEquals(hash1.sidesRotation, hash2.sidesRotation)
    }

    @Test
    fun `Int MIN_VALUE does not crash`() {
        val hash = ClassicIdenticonHash(Int.MIN_VALUE)
        // Just verify all fields are non-negative
        assertTrue(hash.middle >= 0)
        assertTrue(hash.corners >= 0)
        assertTrue(hash.sides >= 0)
        assertTrue(hash.r >= 0)
        assertTrue(hash.g >= 0)
        assertTrue(hash.b >= 0)
    }
}
