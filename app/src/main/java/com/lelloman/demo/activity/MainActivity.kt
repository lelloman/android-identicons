package com.lelloman.demo.activity

import android.content.Intent
import android.os.Bundle
import android.transition.Explode
import android.view.Window
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        window.enterTransition = Explode()
        window.exitTransition = Explode()
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        val viewPager = findViewById<androidx.viewpager2.widget.ViewPager2>(R.id.view_pager)
        val tabLayout = findViewById<com.google.android.material.tabs.TabLayout>(R.id.tab_layout)

        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = fragments.size

            override fun createFragment(position: Int): Fragment {
                return try {
                    fragments[position].getDeclaredConstructor().newInstance()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Fragment()
                }
            }
        }

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
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
