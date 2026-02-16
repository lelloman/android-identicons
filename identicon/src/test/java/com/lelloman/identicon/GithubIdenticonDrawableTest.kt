package com.lelloman.identicon

import com.lelloman.identicon.drawable.GithubIdenticonDrawable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GithubIdenticonDrawableTest {

    @Test
    fun `drawable can be created`() {
        val drawable = GithubIdenticonDrawable(100, 100, 12345)
        assertNotNull(drawable)
    }

    @Test
    fun `dimensions rounded up to multiple of 5`() {
        val drawable = GithubIdenticonDrawable(103, 103, 42)
        assertEquals(105, drawable.width)
        assertEquals(105, drawable.height)
    }

    @Test
    fun `exact multiple of 5 keeps dimensions`() {
        val drawable = GithubIdenticonDrawable(100, 100, 42)
        assertEquals(100, drawable.width)
        assertEquals(100, drawable.height)
    }

    @Test
    fun `same hash produces same color`() {
        val d1 = GithubIdenticonDrawable(100, 100, 12345)
        val d2 = GithubIdenticonDrawable(100, 100, 12345)
        assertEquals(d1.color, d2.color)
    }
}
