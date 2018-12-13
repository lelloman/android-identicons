package com.lelloman.demo.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.lelloman.identicon.drawable.IdenticonDrawable
import com.lelloman.identicon.view.IdenticonView
import java.util.*

abstract class IdenticonFragment : Fragment() {

    private var listener: IdenticonFragmentListener? = null

    protected abstract val adapter: RecyclerViewAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as IdenticonFragmentListener?
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val recyclerView = object : RecyclerView(context!!) {
            override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
                super.onSizeChanged(w, h, oldw, oldh)
                if (w != 0) {
                    val columns = (w / (resources.displayMetrics.density * 64)).toInt()
                    layoutManager = GridLayoutManager(context, columns, GridLayoutManager.VERTICAL, false).apply {
                        scrollToPosition(Integer.MAX_VALUE / 2)
                    }
                }
            }
        }

        recyclerView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        recyclerView.adapter = adapter


        return recyclerView
    }

    companion object {
        val RANDOM = Random()
    }

    abstract inner class RecyclerViewAdapter : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = object : IdenticonView(parent.context) {
                override fun makeIdenticonDrawable(width: Int, height: Int, hash: Int) =
                    this@RecyclerViewAdapter.makeIdenticonDrawable(width, height, hash)

                override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
                    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
                    setMeasuredDimension(measuredWidth, measuredWidth)
                }
            }
            view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val hash = getHashForPosition(position)
            holder.hash = hash
            holder.identiconView.hash = hash
            ViewCompat.setTransitionName(holder.identiconView, hash.toString())
        }

        override fun getItemCount() = Integer.MAX_VALUE

        protected abstract fun getHashForPosition(position: Int): Int
        protected abstract fun makeIdenticonDrawable(width: Int, height: Int, hash: Int): IdenticonDrawable
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val identiconView: IdenticonView = itemView as IdenticonView
        var hash: Int = 0

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            identiconView.transitionName = hash.toString()
            listener?.onIdenticonSelected(hash, identiconView, this@IdenticonFragment)
        }
    }

    interface IdenticonFragmentListener {
        fun onIdenticonSelected(hash: Int, identiconView: ImageView, fragment: IdenticonFragment)
    }
}
