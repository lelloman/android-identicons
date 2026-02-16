package com.lelloman.identicon.view

import android.content.Context
import android.util.AttributeSet

import com.lelloman.identicon.drawable.GithubIdenticonDrawable

@Suppress("unused")
class GithubIdenticonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : IdenticonView(context, attrs) {
    override fun makeIdenticonDrawable(width: Int, height: Int, hash: ByteArray) =
        GithubIdenticonDrawable(width, height, hash)
}
