package com.lelloman.identicon

import com.lelloman.identicon.drawable.GithubIdenticonDrawable
import com.lelloman.identicon.util.toIdenticonHash
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GithubIdenticonDrawableTest {

    @Test
    fun `drawable can be created`() {
        val drawable = GithubIdenticonDrawable(100, 100, 12345.toIdenticonHash())
        assertNotNull(drawable)
    }

    @Test
    fun `dimensions rounded up to multiple of 5`() {
        val drawable = GithubIdenticonDrawable(103, 103, 42.toIdenticonHash())
        assertEquals(105, drawable.width)
        assertEquals(105, drawable.height)
    }

    @Test
    fun `exact multiple of 5 keeps dimensions`() {
        val drawable = GithubIdenticonDrawable(100, 100, 42.toIdenticonHash())
        assertEquals(100, drawable.width)
        assertEquals(100, drawable.height)
    }

    @Test
    fun `same hash produces same color`() {
        val hash = 12345.toIdenticonHash()
        val d1 = GithubIdenticonDrawable(100, 100, hash)
        val d2 = GithubIdenticonDrawable(100, 100, hash)
        assertEquals(d1.color, d2.color)
    }

    @Test
    fun `color has full alpha`() {
        val drawable = GithubIdenticonDrawable(100, 100, 42.toIdenticonHash())
        val alpha = (drawable.color shr 24) and 0xFF
        assertEquals(0xFF, alpha)
    }

    @Test
    fun `color RGB channels in expected range`() {
        for (i in listOf(0, 1, 42, 255, Int.MAX_VALUE)) {
            val drawable = GithubIdenticonDrawable(100, 100, i.toIdenticonHash())
            val r = (drawable.color shr 16) and 0xFF
            val g = (drawable.color shr 8) and 0xFF
            val b = drawable.color and 0xFF
            assertTrue("r=$r out of range for input=$i", r in 80..206)
            assertTrue("g=$g out of range for input=$i", g in 80..206)
            assertTrue("b=$b out of range for input=$i", b in 80..206)
        }
    }
}
