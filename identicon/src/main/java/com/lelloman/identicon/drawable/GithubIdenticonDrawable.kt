package com.lelloman.identicon.drawable

import android.graphics.Canvas
import android.graphics.Paint

import com.lelloman.identicon.util.extractBits

/**
 * Draws an identicon as the ones from github, it uses 15 bits
 * for the tiles and 18 bits (6+6+6) for the RGB color.
 */
class GithubIdenticonDrawable(
    width: Int,
    height: Int,
    hash: ByteArray
) : IdenticonDrawable(
    desiredWidth = width,
    desiredHeight = height,
    hash = hash,
    multipleOf = 5
) {

    private val fgPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { style = Paint.Style.FILL }

    val color: Int
        get() {
            val r = 80 + extractBits(hash, 15, 6) * 2
            val g = 80 + extractBits(hash, 21, 6) * 2
            val b = 80 + extractBits(hash, 27, 6) * 2
            return -0x1000000 + r * 0x10000 + g * 0x100 + b
        }

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
                val draw = extractBits(hash, i++, 1) == 0
                if (!draw) continue

                val startY = (y * h).toFloat()
                val endY = startY + h

                canvas.drawRect(startX.toFloat(), startY, endX.toFloat(), endY, fgPaint)
                if (x != 2)
                    canvas.drawRect(startXMirror.toFloat(), startY, endXMirror.toFloat(), endY, fgPaint)
            }
        }
    }
}
