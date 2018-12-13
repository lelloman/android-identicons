package com.lelloman.demo.fragment

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import com.lelloman.identicon.drawable.ClassicIdenticonTile
import com.lelloman.identicon.util.TileMeasures

class ClassicTilesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val recyclerView = RecyclerView(context!!)

        recyclerView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        recyclerView.adapter = TilesAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


        return recyclerView
    }

    inner class TilesAdapter : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val view = LinearLayout(context)
            view.orientation = LinearLayout.HORIZONTAL
            view.setBackgroundColor(-0x666667)
            view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            val tileView = TileView(context!!)
            val tileSize = (context!!.resources.displayMetrics.density * 32).toInt()
            val tileMargin = tileSize / 4
            var layoutParams = LinearLayout.LayoutParams(tileSize, tileSize)
            layoutParams.setMargins(tileMargin, tileMargin, tileMargin, tileMargin)
            tileView.layoutParams = layoutParams
            tileView.id = TILE_VIEW_ID

            view.addView(tileView)

            val textView = TextView(context)
            textView.id = TEXT_VIEW_ID

            layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
            layoutParams.weight = 1f
            layoutParams.gravity = Gravity.CENTER
            textView.layoutParams = layoutParams

            view.addView(textView)

            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val tile = ClassicIdenticonTile.all[position]
            holder.tileView.setTile(tile)

            val text = String.format("%s - %s%s", position, tile.name, if (position % 4 == 0) "*" else "")
            holder.textView.setText(text)

        }

        override fun getItemCount(): Int {
            return ClassicIdenticonTile.all.size
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tileView: TileView
        val textView: TextView

        init {
            tileView = view.findViewById<View>(TILE_VIEW_ID) as TileView
            textView = view.findViewById<View>(TEXT_VIEW_ID) as TextView
        }
    }

    class TileView(context: Context) : View(context) {

        internal var whitePaint: Paint
        internal var blackPaint: Paint
        internal var tile: ClassicIdenticonTile.Tiles? = null

        init {

            whitePaint = Paint(Paint.ANTI_ALIAS_FLAG)
            whitePaint.color = -0x1
            whitePaint.style = Paint.Style.FILL

            blackPaint = Paint(Paint.ANTI_ALIAS_FLAG)
            blackPaint.color = -0x1000000
            blackPaint.style = Paint.Style.FILL
        }

        fun setTile(tile: ClassicIdenticonTile.Tiles) {
            this.tile = tile
            invalidate()
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            if (tile == null)
                return

            val measures = TileMeasures(canvas.width, canvas.height)

            tile!!.draw(canvas, measures, 0, whitePaint, blackPaint)
        }
    }

    companion object {

        val TILE_VIEW_ID = 1
        val TEXT_VIEW_ID = 2
    }
}