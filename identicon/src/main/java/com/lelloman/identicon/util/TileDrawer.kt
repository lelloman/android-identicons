package com.lelloman.identicon.util

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

/**
 * The base implementation of a tile, the actual drawing of the shape is performed by
 * [TileDrawer.drawer], this class just sets up the rotation and clears the background.
 */
class TileDrawer(private val drawer: (Path, TileMeasures) -> Unit) {

    private val path = Path()

    fun draw(canvas: Canvas, measures: TileMeasures, originalRotation: Int, bgPaint: Paint, fgPaint: Paint) {
        canvas.drawRect(0f, 0f, measures.width.toFloat(), measures.height.toFloat(), bgPaint)

        path.reset()
        val rotation = originalRotation % 360
        if (rotation != 0) {
            canvas.save()
            canvas.rotate(rotation.toFloat(), measures.wMid.toFloat(), measures.hMid.toFloat())
            drawer(path, measures)
            canvas.drawPath(path, fgPaint)
            canvas.restore()
        } else {
            drawer(path, measures)
            canvas.drawPath(path, fgPaint)
        }
    }
}
