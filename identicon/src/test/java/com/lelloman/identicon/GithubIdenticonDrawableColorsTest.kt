package com.lelloman.identicon

import com.lelloman.identicon.drawable.GithubIdenticonDrawable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GithubIdenticonDrawableColorsTest {

    @Test
    fun `COLORS array has 60 entries`() {
        // 4*4*4 = 64 minus 4 grays (where r==g==b) = 60
        assertEquals(60, GithubIdenticonDrawable.COLORS.size)
    }

    @Test
    fun `no gray colors in COLORS array`() {
        for (color in GithubIdenticonDrawable.COLORS) {
            val r = (color shr 16) and 0xFF
            val g = (color shr 8) and 0xFF
            val b = color and 0xFF
            assertTrue(
                "Found gray color: r=$r g=$g b=$b",
                !(r == g && g == b)
            )
        }
    }

    @Test
    fun `all colors have full alpha`() {
        for (color in GithubIdenticonDrawable.COLORS) {
            val alpha = (color shr 24) and 0xFF
            assertEquals("Color ${Integer.toHexString(color)} should have full alpha", 0xFF, alpha)
        }
    }
}
