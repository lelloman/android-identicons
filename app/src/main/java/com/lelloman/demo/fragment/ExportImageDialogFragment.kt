package com.lelloman.demo.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.content.FileProvider
import android.widget.NumberPicker
import com.lelloman.demo.R
import com.lelloman.demo.activity.DetailActivity
import com.lelloman.identicon.drawable.ClassicIdenticonDrawable
import com.lelloman.identicon.drawable.GithubIdenticonDrawable
import com.lelloman.identicon.drawable.IdenticonDrawable
import java.io.File
import java.io.FileOutputStream

class ExportImageDialogFragment : DialogFragment() {

    private var listener: ExportImageListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ExportImageListener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val context = activity

        val view = NumberPicker(context)

        view.minValue = 0
        view.maxValue = SIZES.size - 1

        view.displayedValues = DISPLAYED_SIZES

        return AlertDialog.Builder(context)
            .setTitle(R.string.select_size)
            .setView(view)
            .setPositiveButton(android.R.string.ok) { _, _ -> onSizeSelected(SIZES[view.value]) }
            .setNegativeButton(android.R.string.cancel, null)
            .create()
    }

    private fun onSizeSelected(size: Int) {
        val args = arguments ?: throw IllegalStateException("Fragment argument cannot be null.")
        val type = args.getInt(ARG_TYPE, DetailActivity.TYPE_CLASSIC)
        val hash = args.getInt(ARG_HASH)

        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)

        val drawable = makeIdenticonDrawable(size, type, hash) ?: return

        drawable.draw(Canvas(bitmap))
        val filename = String.format("%s.png", System.currentTimeMillis())

        val context = activity

        val file = File(context!!.cacheDir, filename)
        try {
            file.createNewFile()
            val fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            fos.close()

            if (listener != null) {
                val uri = FileProvider.getUriForFile(context, context.packageName + ".provider", file)
                listener!!.onImageCreated(uri)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun makeIdenticonDrawable(size: Int, type: Int, hash: Int): IdenticonDrawable? {
        when (type) {
            DetailActivity.TYPE_CLASSIC -> return ClassicIdenticonDrawable(size, size, hash)
            DetailActivity.TYPE_GITHUB -> return GithubIdenticonDrawable(size, size, hash)
        }
        return null
    }

    companion object {

        private val SIZES = intArrayOf(32, 64, 128, 256, 512, 1024, 2048)
        private val DISPLAYED_SIZES = arrayOfNulls<String>(SIZES.size)

        init {
            for (i in DISPLAYED_SIZES.indices) {
                val n = SIZES[i]
                DISPLAYED_SIZES[i] = String.format("%sx%s", n, n)
            }
        }

        private const val ARG_HASH = "hash"
        private const val ARG_TYPE = "type"

        fun newInstance(hash: Int, type: Int): ExportImageDialogFragment {
            val output = ExportImageDialogFragment()

            val args = Bundle()
            args.putInt(ARG_HASH, hash)
            args.putInt(ARG_TYPE, type)
            output.arguments = args

            return output
        }
    }

    interface ExportImageListener {
        fun onImageCreated(uri: Uri)
    }
}
