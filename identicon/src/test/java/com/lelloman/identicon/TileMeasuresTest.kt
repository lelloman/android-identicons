package com.lelloman.identicon

import com.lelloman.identicon.util.TileMeasures
import org.junit.Assert.assertEquals
import org.junit.Test

class TileMeasuresTest {

    @Test
    fun `computed values for 120x90`() {
        val m = TileMeasures(120, 90)
        assertEquals(120, m.width)
        assertEquals(90, m.height)
        assertEquals(60, m.wMid)
        assertEquals(45, m.hMid)
        assertEquals(40, m.w3)
        assertEquals(30, m.h3)
        assertEquals(30, m.w4)
        assertEquals(22, m.h4)
        assertEquals(80, m.w32)
        assertEquals(60, m.h32)
        assertEquals(90, m.w43)
        assertEquals(66, m.h43)
        assertEquals(240, m.width2)
        assertEquals(180, m.height2)
    }

    @Test
    fun `computed values for square 100x100`() {
        val m = TileMeasures(100, 100)
        assertEquals(50, m.wMid)
        assertEquals(50, m.hMid)
        assertEquals(33, m.w3)
        assertEquals(33, m.h3)
        assertEquals(25, m.w4)
        assertEquals(25, m.h4)
        assertEquals(66, m.w32)
        assertEquals(66, m.h32)
        assertEquals(75, m.w43)
        assertEquals(75, m.h43)
        assertEquals(200, m.width2)
        assertEquals(200, m.height2)
    }

    @Test
    fun `width and height are preserved`() {
        val m = TileMeasures(7, 13)
        assertEquals(7, m.width)
        assertEquals(13, m.height)
    }
}
