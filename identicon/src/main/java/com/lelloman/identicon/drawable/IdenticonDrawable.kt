package com.lelloman.identicon.drawable

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable

/**
 * Basically a [Bitmap] wrapper, the [Bitmap] size must be known when instantiating it
 * but when drawing it will draw the [Bitmap] to fit the canvas.
 */
abstract class IdenticonDrawable(
    desiredWidth: Int,
    desiredHeight: Int,
    hash: Int,
    multipleOf: Int
) : Drawable() {

    private val bitmapRect: Rect
    private val destinationRect: Rect
    private val bitmap: Bitmap
    private val canvas: Canvas
    private val bitmapPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val width: Int
    val height: Int

    var hash: Int = hash
        set(value) {
            field = value
            onSetHash(value)
            invalidateBitmap()
        }

    init {
        var actualWidth = desiredWidth
        var actualHeight = desiredHeight

        while (actualWidth % multipleOf != 0) {
            actualWidth++
        }
        while (actualHeight % multipleOf != 0) {
            actualHeight++
        }
        this.width = actualWidth
        this.height = actualHeight
        bitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888)
        bitmapRect = Rect(0, 0, actualWidth, actualHeight)
        destinationRect = Rect(0, 0, actualWidth, actualHeight)
        canvas = Canvas(bitmap)
    }

    fun invalidateBitmap() {
        drawBitmap(canvas)
        invalidateSelf()
    }

    protected abstract fun drawBitmap(canvas: Canvas)

    protected open fun onSetHash(newHash: Int) = Unit

    override fun draw(canvas: Canvas) {
        destinationRect.set(0, 0, canvas.width, canvas.height)
        canvas.drawBitmap(bitmap, bitmapRect, destinationRect, bitmapPaint)
    }

    override fun setAlpha(i: Int) {
        bitmapPaint.alpha = i
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        bitmapPaint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return bitmapPaint.alpha
    }
}
