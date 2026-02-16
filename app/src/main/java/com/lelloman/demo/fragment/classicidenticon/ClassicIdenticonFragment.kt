package com.lelloman.demo.fragment.classicidenticon

import com.lelloman.demo.fragment.IdenticonFragment
import com.lelloman.identicon.drawable.ClassicIdenticonDrawable

abstract class ClassicIdenticonFragment : IdenticonFragment() {

    override open val adapter = object : IdenticonFragment.RecyclerViewAdapter() {
        override fun getHashForPosition(position: Int) =
            this@ClassicIdenticonFragment.getHashForPosition(position)

        override fun makeIdenticonDrawable(width: Int, height: Int, hash: ByteArray) =
            ClassicIdenticonDrawable(width, height, hash)
    }

    protected abstract fun getHashForPosition(position: Int): Int
}
