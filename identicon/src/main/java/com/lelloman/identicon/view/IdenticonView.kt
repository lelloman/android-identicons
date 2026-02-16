package com.lelloman.identicon.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import com.lelloman.identicon.R
import com.lelloman.identicon.drawable.IdenticonDrawable
import com.lelloman.identicon.util.toIdenticonHash

/**
 * An [AppCompatImageView] that displays an [IdenticonDrawable], its instantiation is delegated to
 * [IdenticonView.makeIdenticonDrawable].
 */
abstract class IdenticonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    var hash: ByteArray = ByteArray(16)
        set(value) {
            field = value
            identiconDrawable?.let { identiconDrawable ->
                identiconDrawable.hash = value
                invalidate()
            }
        }
    private var identiconDrawable: IdenticonDrawable? = null

    init {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.IdenticonView)
            try {
                val intHash = a.getInt(R.styleable.IdenticonView_hash, 0)
                hash = intHash.toIdenticonHash()
            } catch (exception: Throwable) {
                Log.w(javaClass.simpleName, "Something went wrong when initializing ${javaClass.name} $this")
            } finally {
                a.recycle()
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w > 0 && h > 0) {
            identiconDrawable = makeIdenticonDrawable(width, height, hash)
            setImageDrawable(identiconDrawable)
        }
    }

    protected abstract fun makeIdenticonDrawable(width: Int, height: Int, hash: ByteArray): IdenticonDrawable
}
