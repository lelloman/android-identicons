package com.lelloman.demo.fragment

import com.lelloman.identicon.drawable.GithubIdenticonDrawable
import com.lelloman.identicon.drawable.IdenticonDrawable

class GithubIdenticonFragment : IdenticonFragment() {

    override val adapter: IdenticonFragment.RecyclerViewAdapter
        get() = object : IdenticonFragment.RecyclerViewAdapter() {
            override fun getHashForPosition(position: Int): Int {
                return IdenticonFragment.RANDOM.nextInt()
            }

            override fun makeIdenticonDrawable(width: Int, height: Int, hash: ByteArray): IdenticonDrawable {
                return GithubIdenticonDrawable(width, height, hash)
            }
        }
}
