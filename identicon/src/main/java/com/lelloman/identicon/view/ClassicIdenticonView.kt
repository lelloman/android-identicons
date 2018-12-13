package com.lelloman.identicon.view

import android.content.Context
import android.util.AttributeSet

import com.lelloman.identicon.drawable.ClassicIdenticonDrawable

@Suppress("unused")
class ClassicIdenticonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : IdenticonView(context, attrs) {
    override fun makeIdenticonDrawable(width: Int, height: Int, hash: Int) =
        ClassicIdenticonDrawable(width, height, hash)
}
