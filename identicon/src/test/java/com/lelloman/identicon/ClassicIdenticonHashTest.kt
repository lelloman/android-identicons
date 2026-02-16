package com.lelloman.identicon

import com.lelloman.identicon.drawable.ClassicIdenticonHash
import com.lelloman.identicon.util.toIdenticonHash
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ClassicIdenticonHashTest {

    private fun hashFor(i: Int) = ClassicIdenticonHash(i.toIdenticonHash())

    @Test
    fun `middle is in range 0-8`() {
        for (i in listOf(0, 1, -1, 42, 255, Int.MIN_VALUE, Int.MAX_VALUE)) {
            val hash = hashFor(i)
            assertTrue("middle=${hash.middle} for input=$i", hash.middle in 0..8)
        }
    }

    @Test
    fun `corners in range 0-36`() {
        for (i in listOf(0, 1, -1, 42, 255, Int.MIN_VALUE, Int.MAX_VALUE)) {
            val hash = hashFor(i)
            assertTrue("corners=${hash.corners} for input=$i", hash.corners in 0..36)
        }
    }

    @Test
    fun `sides in range 0-36`() {
        for (i in listOf(0, 1, -1, 42, 255, Int.MIN_VALUE, Int.MAX_VALUE)) {
            val hash = hashFor(i)
            assertTrue("sides=${hash.sides} for input=$i", hash.sides in 0..36)
        }
    }

    @Test
    fun `invert flags are 0 or 1`() {
        for (i in listOf(0, 1, -1, 42, 255, Int.MIN_VALUE, Int.MAX_VALUE)) {
            val hash = hashFor(i)
            assertTrue("invertMiddle=${hash.invertMiddle}", hash.invertMiddle in 0..1)
            assertTrue("invertCorners=${hash.invertCorners}", hash.invertCorners in 0..1)
            assertTrue("invertSides=${hash.invertSides}", hash.invertSides in 0..1)
        }
    }

    @Test
    fun `rgb values in range 0-63`() {
        for (i in listOf(0, 1, -1, 42, 255, Int.MIN_VALUE, Int.MAX_VALUE)) {
            val hash = hashFor(i)
            assertTrue("r=${hash.r}", hash.r in 0..63)
            assertTrue("g=${hash.g}", hash.g in 0..63)
            assertTrue("b=${hash.b}", hash.b in 0..63)
        }
    }

    @Test
    fun `rotation values in range 0-3`() {
        for (i in listOf(0, 1, -1, 42, 255, Int.MIN_VALUE, Int.MAX_VALUE)) {
            val hash = hashFor(i)
            assertTrue("cornersRotation=${hash.cornersRotation}", hash.cornersRotation in 0..3)
            assertTrue("sidesRotation=${hash.sidesRotation}", hash.sidesRotation in 0..3)
            assertTrue("middleRotation=${hash.middleRotation}", hash.middleRotation in 0..3)
        }
    }

    @Test
    fun `same hash produces same output`() {
        val md5 = 12345.toIdenticonHash()
        val hash1 = ClassicIdenticonHash(md5)
        val hash2 = ClassicIdenticonHash(md5)
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
        assertEquals(hash1.middleRotation, hash2.middleRotation)
    }

    @Test
    fun `all fields are non-negative`() {
        for (i in listOf(0, 1, -1, Int.MIN_VALUE, Int.MAX_VALUE)) {
            val hash = hashFor(i)
            assertTrue(hash.middle >= 0)
            assertTrue(hash.corners >= 0)
            assertTrue(hash.sides >= 0)
            assertTrue(hash.r >= 0)
            assertTrue(hash.g >= 0)
            assertTrue(hash.b >= 0)
            assertTrue(hash.middleRotation >= 0)
        }
    }
}
