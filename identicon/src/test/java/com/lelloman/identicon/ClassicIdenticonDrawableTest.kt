package com.lelloman.identicon

import com.lelloman.identicon.drawable.ClassicIdenticonDrawable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ClassicIdenticonDrawableTest {

    @Test
    fun `drawable can be created`() {
        val drawable = ClassicIdenticonDrawable(90, 90, 12345)
        assertNotNull(drawable)
    }

    @Test
    fun `dimensions rounded up to multiple of 3`() {
        val drawable = ClassicIdenticonDrawable(100, 100, 42)
        assertEquals(102, drawable.width)
        assertEquals(102, drawable.height)
    }

    @Test
    fun `exact multiple of 3 keeps dimensions`() {
        val drawable = ClassicIdenticonDrawable(90, 90, 42)
        assertEquals(90, drawable.width)
        assertEquals(90, drawable.height)
    }

    @Test
    fun `hash change updates drawable`() {
        val drawable = ClassicIdenticonDrawable(90, 90, 1)
        drawable.hash = 2
        assertEquals(2, drawable.hash)
    }
}
