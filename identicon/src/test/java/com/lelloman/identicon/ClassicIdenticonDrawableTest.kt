package com.lelloman.identicon

import com.lelloman.identicon.drawable.ClassicIdenticonDrawable
import com.lelloman.identicon.util.toIdenticonHash
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ClassicIdenticonDrawableTest {

    @Test
    fun `drawable can be created`() {
        val drawable = ClassicIdenticonDrawable(90, 90, 12345.toIdenticonHash())
        assertNotNull(drawable)
    }

    @Test
    fun `dimensions rounded up to multiple of 3`() {
        val drawable = ClassicIdenticonDrawable(100, 100, 42.toIdenticonHash())
        assertEquals(102, drawable.width)
        assertEquals(102, drawable.height)
    }

    @Test
    fun `exact multiple of 3 keeps dimensions`() {
        val drawable = ClassicIdenticonDrawable(90, 90, 42.toIdenticonHash())
        assertEquals(90, drawable.width)
        assertEquals(90, drawable.height)
    }

    @Test
    fun `hash change updates drawable`() {
        val hash1 = 1.toIdenticonHash()
        val hash2 = 2.toIdenticonHash()
        val drawable = ClassicIdenticonDrawable(90, 90, hash1)
        drawable.hash = hash2
        assertArrayEquals(hash2, drawable.hash)
    }
}
