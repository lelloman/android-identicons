package com.lelloman.demo.fragment.classicidenticon

import com.lelloman.demo.fragment.IdenticonFragment
import com.lelloman.identicon.drawable.ClassicIdenticonDrawable
import com.lelloman.identicon.util.toSequentialBytes

class SequenceClassicIdenticonFragment : ClassicIdenticonFragment() {

    private val offset: Int = IdenticonFragment.RANDOM.nextInt()

    override fun getHashForPosition(position: Int) = position + offset

    override val adapter = object : IdenticonFragment.RecyclerViewAdapter() {
        override fun getHashForPosition(position: Int) =
            this@SequenceClassicIdenticonFragment.getHashForPosition(position)

        override fun makeIdenticonDrawable(width: Int, height: Int, hash: ByteArray) =
            ClassicIdenticonDrawable(width, height, hash)

        override fun hashToBytes(hash: Int) = hash.toSequentialBytes()
    }
}
