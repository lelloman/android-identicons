package com.lelloman.identicon.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import com.lelloman.identicon.drawable.IdenticonDrawable

/**
 * An [ImageView] that displays an [IdenticonDrawable], its instantiation is delegated to
 * [IdenticonView.makeIdenticonDrawable].
 */
@SuppressLint("AppCompatCustomView")
abstract class IdenticonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ImageView(context, attrs) {

    var hash: Int = 0
        set(value) {
            field = value
            identiconDrawable?.let { identiconDrawable ->
                identiconDrawable.hash = value
                invalidate()
            }
        }
    private var identiconDrawable: IdenticonDrawable? = null

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w > 0 && h > 0) {
            identiconDrawable = makeIdenticonDrawable(width, height, hash)
            setImageDrawable(identiconDrawable)
        }
    }

    protected abstract fun makeIdenticonDrawable(width: Int, height: Int, hash: Int): IdenticonDrawable
}
