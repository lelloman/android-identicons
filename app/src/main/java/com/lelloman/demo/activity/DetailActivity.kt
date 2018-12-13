package com.lelloman.demo.activity

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.ViewTreeObserver
import android.widget.ImageView
import com.lelloman.demo.R
import com.lelloman.demo.fragment.ExportImageDialogFragment
import com.lelloman.identicon.drawable.ClassicIdenticonDrawable
import com.lelloman.identicon.drawable.GithubIdenticonDrawable
import java.util.*

class DetailActivity : AppCompatActivity(), ExportImageDialogFragment.ExportImageListener {

    private var hash: Int = 0
    private var type: Int = 0

    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
    private val imageView by lazy { findViewById<ImageView>(R.id.image_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""

        intent?.let {
            val transitionName = it.getStringExtra(EXTRA_TRANSITION_NAME)
            type = it.getIntExtra(EXTRA_IDENTICON_TYPE, TYPE_CLASSIC)
            hash = it.getIntExtra(EXTRA_HASH, Random().nextInt())

            imageView.transitionName = transitionName
        }

        window.decorView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                var drawable: Drawable? = null

                val size = Math.min(imageView.width, imageView.height)
                when (type) {
                    TYPE_CLASSIC -> drawable = ClassicIdenticonDrawable(size, size, hash)
                    TYPE_GITHUB -> drawable = GithubIdenticonDrawable(size, size, hash)
                }

                val layoutParams = imageView.layoutParams
                layoutParams.height = size
                layoutParams.width = size
                imageView.layoutParams = layoutParams

                if (drawable != null)
                    imageView.setImageDrawable(drawable)

                window.decorView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_export -> {
            exportImage()
            true
        }
        else -> false
    }

    private fun exportImage() {
        val fragment = ExportImageDialogFragment.newInstance(hash, type)
        fragment.show(fragmentManager, ExportImageDialogFragment::class.java.simpleName)
    }

    override fun onImageCreated(uri: Uri) {
        val shareIntent = Intent(Intent.ACTION_SEND)
            .setType("image/png")
            .putExtra(Intent.EXTRA_STREAM, uri)
        startActivity(Intent.createChooser(shareIntent, "Share Image"))
    }

    companion object {
        const val EXTRA_TRANSITION_NAME = "transitionName"
        const val EXTRA_IDENTICON_TYPE = "type"
        const val EXTRA_HASH = "hash"
        const val TYPE_CLASSIC = 0
        const val TYPE_GITHUB = 1
    }
}
