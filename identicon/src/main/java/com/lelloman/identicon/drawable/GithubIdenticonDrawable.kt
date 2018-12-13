package com.lelloman.identicon.drawable


import android.graphics.Canvas
import android.graphics.Paint


/**
 * Draws an identicon as the ones from github, it just uses 15bits
 * for the tiles and 6bits - 4 values for the colors.
 */
class GithubIdenticonDrawable(
    width: Int,
    height: Int,
    hash: Int
) : IdenticonDrawable(
    desiredWidth = width,
    desiredHeight = height,
    hash = hash,
    multipleOf = 5
) {

    private val fgPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { style = Paint.Style.FILL }

    val color: Int get() = COLORS[Math.abs((hash shr 15) % COLORS.size)]

    init {
        invalidateBitmap()
    }

    override fun drawBitmap(canvas: Canvas) {

        fgPaint.color = color

        val w = (canvas.width / 5f).toInt()
        val h = (canvas.height / 5f).toInt()

        canvas.drawColor(-0x1)

        var i = 0
        for (x in 0..2) {
            val startX = x * w
            val endX = startX + w

            val startXMirror = (4 - x) * w
            val endXMirror = startXMirror + w

            for (y in 0..4) {
                val value = hash shr i++
                val draw = value % 2 == 0
                if (!draw) continue

                val startY = (y * h).toFloat()
                val endY = startY + h

                canvas.drawRect(startX.toFloat(), startY, endX.toFloat(), endY, fgPaint)
                if (x != 2)
                    canvas.drawRect(startXMirror.toFloat(), startY, endXMirror.toFloat(), endY, fgPaint)
            }
        }
    }

    companion object {
        // create 4*4*4 colors but remove the grays
        var COLORS: IntArray
        init {
            val n = 4
            val size = n * n * n - n
            COLORS = IntArray(size)
            var index = 0
            val colorBase = 150
            val colorTot = 100 / n
            for (i in 0 until n) {
                for (j in 0 until n) {
                    for (k in 0 until n) {
                        if (i == j && j == k) continue
                        val r = colorBase + i * colorTot
                        val g = colorBase + j * colorTot
                        val b = colorBase + k * colorTot

                        val color = -0x1000000 + r * 0x10000 + g * 0x100 + b
                        COLORS[index++] = color
                    }
                }
            }
        }
    }

}
