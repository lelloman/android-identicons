package com.lelloman.demo.fragment

import com.lelloman.identicon.drawable.IdenticonDrawable
import com.lelloman.identicon.drawable.GithubIdenticonDrawable


class GithubIdenticonFragment : IdenticonFragment() {

    protected override val adapter: IdenticonFragment.RecyclerViewAdapter
        get() = object : IdenticonFragment.RecyclerViewAdapter() {
            override fun getHashForPosition(position: Int): Int {
                return IdenticonFragment.RANDOM.nextInt()
            }

            override fun makeIdenticonDrawable(width: Int, height: Int, hash: Int): IdenticonDrawable {
                return GithubIdenticonDrawable(width, height, hash)
            }
        }
}
