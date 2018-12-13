package com.lelloman.identicon.util

import android.graphics.Path

/**
 * A [Path] with a few utility methods.
 */
class Path2 : Path() {

    fun addTriangle(x0: Int, y0: Int, x1: Int, y1: Int, x2: Int, y2: Int) {
        moveTo(x0.toFloat(), y0.toFloat())
        lineTo(x1.toFloat(), y1.toFloat())
        lineTo(x2.toFloat(), y2.toFloat())
        lineTo(x0.toFloat(), y0.toFloat())
    }

    fun addRectangle(left: Int, top: Int, right: Int, bottom: Int) {
        moveTo(left.toFloat(), top.toFloat())
        lineTo(right.toFloat(), top.toFloat())
        lineTo(right.toFloat(), bottom.toFloat())
        lineTo(left.toFloat(), bottom.toFloat())
        lineTo(left.toFloat(), top.toFloat())
    }

    fun addPolygon(x0: Int, y0: Int, x1: Int, y1: Int, x2: Int, y2: Int, x3: Int, y3: Int) {
        moveTo(x0.toFloat(), y0.toFloat())
        lineTo(x1.toFloat(), y1.toFloat())
        lineTo(x2.toFloat(), y2.toFloat())
        lineTo(x3.toFloat(), y3.toFloat())
        lineTo(x0.toFloat(), y0.toFloat())
    }
}
