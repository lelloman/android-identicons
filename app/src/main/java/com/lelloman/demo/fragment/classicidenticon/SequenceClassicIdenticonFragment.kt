package com.lelloman.demo.fragment.classicidenticon

import com.lelloman.demo.fragment.IdenticonFragment


class SequenceClassicIdenticonFragment : ClassicIdenticonFragment() {

    private val offset: Int = IdenticonFragment.RANDOM.nextInt()

    override fun getHashForPosition(position: Int) = position + offset
}
