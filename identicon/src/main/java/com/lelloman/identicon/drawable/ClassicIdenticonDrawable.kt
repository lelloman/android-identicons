package com.lelloman.identicon.drawable

import android.graphics.Canvas
import android.graphics.Paint

import com.lelloman.identicon.util.TileMeasures

/**
 * Draws the identicon given an [ClassicIdenticonHash].
 */
class ClassicIdenticonDrawable(
    width: Int,
    height: Int,
    hash: Int
) : IdenticonDrawable(
    desiredWidth = width,
    desiredHeight = height,
    hash = hash,
    multipleOf = 3
) {

    private var identiconHash: ClassicIdenticonHash? = null

    private val bgPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val fgPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val tileMeasure: TileMeasures

    private val color: Int
        get() {
            val r = 100 + identiconHash!!.r * 8
            val g = 100 + identiconHash!!.g * 8
            val b = 100 + identiconHash!!.b * 8

            return -0x1000000 + r * 0x10000 + g * 0x100 + b
        }

    init {
        onSetHash(hash)

        bgPaint.style = Paint.Style.FILL
        fgPaint.style = Paint.Style.FILL

        bgPaint.color = -0x1
        fgPaint.color = -0x1000000

        tileMeasure = TileMeasures(width / 3, height / 3)

        invalidateBitmap()
    }

    override fun onSetHash(newHash: Int) {
        identiconHash = ClassicIdenticonHash(newHash)
    }

    override fun drawBitmap(canvas: Canvas) {

        if (identiconHash == null)
            return

        fgPaint.color = color

        canvas.drawColor(-0x1)

        drawMiddle(canvas)
        drawCorners(canvas)
        drawSides(canvas)
    }

    private fun getTile(position: Int): ClassicIdenticonTile.Tiles =
        ClassicIdenticonTile.all[position]

    private fun drawMiddle(canvas: Canvas) {

        val invert = identiconHash!!.invertMiddle == 0

        val fg = if (invert) fgPaint else bgPaint
        val bg = if (invert) bgPaint else fgPaint

        canvas.save()
        canvas.translate(tileMeasure.width.toFloat(), tileMeasure.height.toFloat())
        getTile(identiconHash!!.middle).draw(canvas, tileMeasure, 0, bg, fg)
        canvas.restore()

    }

    private fun drawCorners(canvas: Canvas) {

        val drawer = getTile(identiconHash!!.corners)

        val invert = identiconHash!!.invertCorners == 0
        val rotation = identiconHash!!.cornersRotation * 90

        val fg = if (invert) fgPaint else bgPaint
        val bg = if (invert) bgPaint else fgPaint

        drawer.draw(canvas, tileMeasure, 0 + rotation, bg, fg)

        canvas.save()
        canvas.translate(tileMeasure.width2.toFloat(), 0f)
        drawer.draw(canvas, tileMeasure, 90 + rotation, bg, fg)
        canvas.restore()

        canvas.save()
        canvas.translate(tileMeasure.width2.toFloat(), tileMeasure.height2.toFloat())
        drawer.draw(canvas, tileMeasure, 180 + rotation, bg, fg)
        canvas.restore()

        canvas.save()
        canvas.translate(0f, tileMeasure.height2.toFloat())
        drawer.draw(canvas, tileMeasure, 270 + rotation, bg, fg)
        canvas.restore()
    }

    private fun drawSides(canvas: Canvas) {

        val drawer = getTile(identiconHash!!.sides)

        val invert = identiconHash!!.invertSides == 0
        val rotation = identiconHash!!.sidesRotation * 90

        val fg = if (invert) fgPaint else bgPaint
        val bg = if (invert) bgPaint else fgPaint

        canvas.save()
        canvas.translate(0f, tileMeasure.height.toFloat())
        drawer.draw(canvas, tileMeasure, 0 + rotation, bg, fg)
        canvas.restore()

        canvas.save()
        canvas.translate(tileMeasure.width.toFloat(), tileMeasure.height2.toFloat())
        drawer.draw(canvas, tileMeasure, 90 + rotation, bg, fg)
        canvas.restore()

        canvas.save()
        canvas.translate(tileMeasure.width2.toFloat(), tileMeasure.height.toFloat())
        drawer.draw(canvas, tileMeasure, 180 + rotation, bg, fg)
        canvas.restore()

        canvas.save()
        canvas.translate(tileMeasure.width.toFloat(), 0f)
        drawer.draw(canvas, tileMeasure, 270 + rotation, bg, fg)
        canvas.restore()
    }
}
