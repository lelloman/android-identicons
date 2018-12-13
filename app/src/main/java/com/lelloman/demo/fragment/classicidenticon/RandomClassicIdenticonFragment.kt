package com.lelloman.demo.fragment.classicidenticon

import com.lelloman.demo.fragment.IdenticonFragment

class RandomClassicIdenticonFragment : ClassicIdenticonFragment() {
    override fun getHashForPosition(position: Int) = IdenticonFragment.RANDOM.nextInt()
}
