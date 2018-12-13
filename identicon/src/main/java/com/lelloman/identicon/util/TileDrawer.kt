package com.lelloman.identicon.util

import android.graphics.Canvas
import android.graphics.Paint

/**
 * The base implementation of a tile, the actual drawing of the shape is performed in
 * [TileDrawer.onDraw],this one just sets up the rotation and clears the background.
 */
abstract class TileDrawer {

    fun draw(canvas: Canvas, measures: TileMeasures, rotation: Int, bgPaint: Paint, fgPaint: Paint) {
        var rotation = rotation

        canvas.drawRect(0f, 0f, measures.width.toFloat(), measures.height.toFloat(), bgPaint)

        val path = Path2()

        while (rotation > 360) {
            rotation -= 360
        }

        if (rotation != 0) {
            canvas.save()
            canvas.rotate(rotation.toFloat(), measures.wMid.toFloat(), measures.hMid.toFloat())
            onDraw(path, measures)
            canvas.drawPath(path, fgPaint)
            canvas.restore()
        } else {
            onDraw(path, measures)
            canvas.drawPath(path, fgPaint)
        }
    }

    abstract fun onDraw(path: Path2, measures: TileMeasures)

}
