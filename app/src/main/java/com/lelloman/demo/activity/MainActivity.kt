package com.lelloman.demo.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.transition.Explode
import android.view.Window
import android.widget.ImageView
import com.lelloman.demo.R
import com.lelloman.demo.fragment.ClassicTilesFragment
import com.lelloman.demo.fragment.GithubIdenticonFragment
import com.lelloman.demo.fragment.IdenticonFragment
import com.lelloman.demo.fragment.classicidenticon.RandomClassicIdenticonFragment
import com.lelloman.demo.fragment.classicidenticon.SequenceClassicIdenticonFragment
import com.lelloman.identicon.drawable.ClassicIdenticonDrawable
import com.lelloman.identicon.drawable.GithubIdenticonDrawable

class MainActivity : AppCompatActivity(), IdenticonFragment.IdenticonFragmentListener {

    internal var fragments = arrayOf(RandomClassicIdenticonFragment::class.java, SequenceClassicIdenticonFragment::class.java, ClassicTilesFragment::class.java, GithubIdenticonFragment::class.java)
    internal var titles = arrayOf("Random", "Sequence", "Tiles", "Github")

    private val pager by lazy { findViewById<ViewPager>(R.id.view_pager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            window.enterTransition = Explode()
            window.exitTransition = Explode()
        }
        setContentView(R.layout.activity_main)
        pager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return fragments.size
            }

            override fun getItem(position: Int): Fragment? {
                return try {
                    fragments[position].newInstance()
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return titles[position]
            }
        }
    }

    override fun onIdenticonSelected(hash: Int, identiconView: ImageView, fragment: IdenticonFragment) {
        val type = when (identiconView.drawable) {
            is GithubIdenticonDrawable -> DetailActivity.TYPE_GITHUB
            is ClassicIdenticonDrawable -> DetailActivity.TYPE_CLASSIC
            else -> DetailActivity.TYPE_CLASSIC
        }

        val intent = Intent(this, DetailActivity::class.java)
            .putExtra(DetailActivity.EXTRA_TRANSITION_NAME, hash.toString())
            .putExtra(DetailActivity.EXTRA_HASH, hash)
            .putExtra(DetailActivity.EXTRA_IDENTICON_TYPE, type)

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, identiconView, hash.toString())

        startActivity(intent, options.toBundle())
    }
}
