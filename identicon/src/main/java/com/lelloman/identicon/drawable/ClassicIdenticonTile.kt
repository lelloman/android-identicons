package com.lelloman.identicon.drawable

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

import com.lelloman.identicon.util.TileDrawer
import com.lelloman.identicon.util.TileMeasures
import com.lelloman.identicon.util.addPolygon
import com.lelloman.identicon.util.addRectangle
import com.lelloman.identicon.util.addTriangle

/**
 * Collection of all shapes, 37 of them in total.
 * The tiles at positions %4 = 0 are symmetric in both axis
 * and are the ones that can be used as central tile.
 */
object ClassicIdenticonTile {

    /**
     * drawing values, 0-36
     */
    var all = arrayOf(

        Tiles.FOUR_SQUARES, Tiles.HALF_SQUARE_TRIANGLE, Tiles.BIG_TRIANGLE, Tiles.HALF_SQUARE_RECTANGLE,

        Tiles.ROTATED_SQUARE, Tiles.SPEAR_TIP, Tiles.THREE_TRIANGLES, Tiles.SLIM_TRIANGLE,

        Tiles.LITTLE_SQUARE, Tiles.TWO_TRIANGLES, Tiles.LITTLE_SIDE_SQUARE, Tiles.MARLBORO_TRIANGLE,

        Tiles.FOUR_TRIANGLES, Tiles.LITTLE_TRIANGLE_INSIDE, Tiles.LITTLE_TRIANGLE_TIP, Tiles.TWO_SQUARES_DIAGONAL,

        Tiles.LITTLE_ROTATED_SQUARE, Tiles.SHIFTED_MARLBORO_TRIANGLE, Tiles.TUNNELING_TRIANGLES, Tiles.TWO_TIPS_TRIANGLES,

        Tiles.FOUR_TRIANGLES_FACING_CENTER, Tiles.TWO_SQUARES_STRAIGHT_LINE, Tiles.TWO_TRAPEZOIDS, Tiles.ARROW,

        Tiles.ROTATE_SQUARE_WITH_HOLE, Tiles.TWO_OPPOSITE_TRIANGLES, Tiles.TRIANGLE_SANDWICH, Tiles.SPIKE,

        Tiles.FOUR_TRIANGLES_STAR, Tiles.BIG_TRIANGLE_TIP, Tiles.DIAMOND, Tiles.TWO_OPPOSITE_TRIANGLES_BIG,

        Tiles.CROSS, Tiles.CHEVRON, Tiles.L_SHAPE, Tiles.HOURGLASS, Tiles.STEPPED_DIAGONAL)

    /**
     * The 9 symmetric tiles that can be used as the middle tile.
     */
    val symmetric = arrayOf(
        Tiles.FOUR_SQUARES, Tiles.ROTATED_SQUARE, Tiles.LITTLE_SQUARE, Tiles.FOUR_TRIANGLES,
        Tiles.LITTLE_ROTATED_SQUARE, Tiles.FOUR_TRIANGLES_FACING_CENTER, Tiles.ROTATE_SQUARE_WITH_HOLE,
        Tiles.FOUR_TRIANGLES_STAR, Tiles.CROSS
    )

    enum class Tiles(drawer: (Path, TileMeasures) -> Unit) {
        // 1
        FOUR_SQUARES({ path, meas ->
            path.addRectangle(0, 0, meas.w3, meas.h3)
            path.addRectangle(meas.w32, 0, meas.width, meas.h3)
            path.addRectangle(0, meas.h32, meas.w3, meas.height)
            path.addRectangle(meas.w32, meas.h32, meas.width, meas.height)
        }),
        // 2
        HALF_SQUARE_TRIANGLE({ path, meas ->
            path.addTriangle(0, 0, meas.width, 0, 0, meas.height)
        }),
        // 3
        BIG_TRIANGLE({ path, meas ->
            path.addTriangle(meas.wMid, 0, 0, meas.height, meas.width, meas.height)
        }),
        // 4
        HALF_SQUARE_RECTANGLE({ path, meas ->
            path.addRectangle(0, 0, meas.wMid, meas.height)
        }),
        // 5
        ROTATED_SQUARE({ path, meas ->
            path.addPolygon(meas.wMid, 0, meas.width, meas.hMid, meas.wMid, meas.height, 0, meas.hMid)
        }),
        // 6
        SPEAR_TIP({ path, meas ->
            path.addPolygon(0, 0, meas.width, meas.hMid, meas.width, meas.height, meas.wMid, meas.height)
        }),
        // 7
        THREE_TRIANGLES({ path, meas ->
            path.addTriangle(meas.wMid, 0, meas.w43, meas.hMid, meas.w4, meas.hMid)
            path.addTriangle(0, meas.height, meas.w4, meas.hMid, meas.wMid, meas.height)
            path.addTriangle(meas.width, meas.height, meas.w43, meas.hMid, meas.wMid, meas.height)
        }),
        // 8
        SLIM_TRIANGLE({ path, meas ->
            path.addTriangle(0, 0, meas.width, meas.hMid, meas.wMid, meas.height)
        }),
        // 9
        LITTLE_SQUARE({ path, meas ->
            path.addRectangle(meas.w4, meas.h4, meas.w43, meas.h43)
        }),
        // 10
        TWO_TRIANGLES({ path, meas ->
            path.addTriangle(0, meas.height, 0, meas.hMid, meas.wMid, meas.hMid)
            path.addTriangle(meas.width, 0, meas.wMid, 0, meas.wMid, meas.hMid)
        }),
        // 11
        LITTLE_SIDE_SQUARE({ path, meas ->
            path.addRectangle(0, 0, meas.wMid, meas.hMid)
        }),
        // 12
        MARLBORO_TRIANGLE({ path, meas ->
            path.addTriangle(0, meas.height, meas.width, meas.height, meas.wMid, meas.hMid)
        }),
        // 13
        FOUR_TRIANGLES({ path, meas ->
            path.addTriangle(meas.wMid, 0, meas.w4, meas.h4, meas.w43, meas.h4)
            path.addTriangle(meas.width, meas.hMid, meas.w43, meas.h43, meas.w43, meas.h4)
            path.addTriangle(meas.wMid, meas.height, meas.w4, meas.h43, meas.w43, meas.h43)
            path.addTriangle(0, meas.hMid, meas.w4, meas.h4, meas.w4, meas.h43)
        }),
        // 14
        LITTLE_TRIANGLE_INSIDE({ path, meas ->
            path.addTriangle(meas.wMid, 0, meas.wMid, meas.hMid, 0, meas.hMid)
        }),
        // 15
        LITTLE_TRIANGLE_TIP({ path, meas ->
            path.addTriangle(meas.wMid, 0, 0, meas.hMid, 0, 0)
        }),
        // 16
        TWO_SQUARES_DIAGONAL({ path, meas ->
            path.addRectangle(0, 0, meas.wMid, meas.hMid)
            path.addRectangle(meas.wMid, meas.hMid, meas.width, meas.height)
        }),
        // 17
        LITTLE_ROTATED_SQUARE({ path, meas ->
            path.addPolygon(meas.wMid, meas.h4, meas.w43, meas.hMid, meas.wMid, meas.h43, meas.w4, meas.hMid)
        }),
        // 18
        SHIFTED_MARLBORO_TRIANGLE({ path, meas ->
            path.addTriangle(meas.wMid, 0, meas.width, meas.hMid, 0, meas.hMid)
        }),
        // 19
        TUNNELING_TRIANGLES({ path, meas ->
            path.addTriangle(0, 0, meas.width, 0, 0, meas.hMid)
            path.addTriangle(meas.width, meas.height, 0, meas.height, meas.width, meas.hMid)
        }),
        // 20
        TWO_TIPS_TRIANGLES({ path, meas ->
            path.addTriangle(meas.wMid, 0, 0, meas.hMid, 0, 0)
            path.addTriangle(meas.wMid, meas.height, meas.width, meas.hMid, meas.width, meas.height)
        }),
        // 21
        FOUR_TRIANGLES_FACING_CENTER({ path, meas ->
            path.addTriangle(0, 0, meas.width, 0, meas.wMid, meas.h3)
            path.addTriangle(meas.width, 0, meas.width, meas.height, meas.w32, meas.hMid)
            path.addTriangle(meas.width, meas.height, 0, meas.height, meas.wMid, meas.h32)
            path.addTriangle(0, meas.height, 0, 0, meas.w3, meas.hMid)
        }),
        // 22
        TWO_SQUARES_STRAIGHT_LINE({ path, meas ->
            path.addPolygon(meas.wMid, 0, meas.w43, meas.h4, meas.wMid, meas.hMid, meas.w4, meas.h4)
            path.addPolygon(meas.wMid, meas.hMid, meas.w43, meas.h43, meas.wMid, meas.height, meas.w4, meas.h43)
        }),
        // 23
        TWO_TRAPEZOIDS({ path, meas ->
            path.addPolygon(0, 0, meas.wMid, 0, meas.wMid, meas.h4, 0, meas.hMid)
            path.addPolygon(meas.width, meas.height, meas.width, meas.hMid, meas.wMid, meas.h43, meas.wMid, meas.height)
        }),
        // 24
        ARROW({ path, meas ->
            path.addPolygon(meas.width, 0, meas.wMid, meas.hMid, meas.width, meas.height, 0, meas.hMid)
        }),
        // 25
        ROTATE_SQUARE_WITH_HOLE({ path, meas ->
            path.fillType = Path.FillType.EVEN_ODD

            path.moveTo(meas.wMid.toFloat(), 0f)
            path.lineTo(meas.width.toFloat(), meas.hMid.toFloat())
            path.lineTo(meas.wMid.toFloat(), meas.height.toFloat())
            path.lineTo(0f, meas.hMid.toFloat())
            path.lineTo(meas.wMid.toFloat(), 0f)
            path.close()

            path.moveTo(meas.wMid.toFloat(), meas.h4.toFloat())
            path.lineTo(meas.w43.toFloat(), meas.hMid.toFloat())
            path.lineTo(meas.wMid.toFloat(), meas.h43.toFloat())
            path.lineTo(meas.w4.toFloat(), meas.hMid.toFloat())
            path.lineTo(meas.wMid.toFloat(), meas.h4.toFloat())
            path.close()
        }),
        // 26
        TWO_OPPOSITE_TRIANGLES({ path, meas ->
            path.addTriangle(0, 0, meas.width, 0, meas.wMid, meas.h4)
            path.addTriangle(meas.width, meas.height, 0, meas.height, meas.wMid, meas.h43)
        }),
        // 27
        TRIANGLE_SANDWICH({ path, meas ->
            path.addTriangle(0, 0, meas.width, 0, meas.wMid, meas.h4)
            path.addPolygon(meas.wMid, meas.h4, meas.w4, meas.hMid, meas.wMid, meas.h43, meas.w43, meas.hMid)
            path.addTriangle(meas.width, meas.height, 0, meas.height, meas.wMid, meas.h43)
        }),
        // 28
        SPIKE({ path, meas ->
            path.addTriangle(0, 0, meas.width, meas.hMid, meas.width, meas.height)
        }),
        // 29
        FOUR_TRIANGLES_STAR({ path, meas ->
            path.addTriangle(0, 0, meas.wMid, meas.h4, meas.w4, meas.hMid)
            path.addTriangle(meas.width, 0, meas.w43, meas.hMid, meas.wMid, meas.h4)
            path.addTriangle(meas.width, meas.height, meas.wMid, meas.h43, meas.w43, meas.hMid)
            path.addTriangle(0, meas.height, meas.w4, meas.hMid, meas.wMid, meas.h43)
        }),
        // 30
        BIG_TRIANGLE_TIP({ path, meas ->
            path.addTriangle(0, 0, meas.width, 0, 0, meas.hMid)
        }),
        // 31
        DIAMOND({ path, meas ->
            path.addPolygon(0, meas.hMid, meas.wMid, meas.h4, meas.width, meas.hMid, meas.wMid, meas.h43)
        }),
        // 32
        TWO_OPPOSITE_TRIANGLES_BIG({ path, meas ->
            path.addTriangle(0, 0, meas.wMid, meas.hMid, 0, meas.height)
            path.addTriangle(meas.width, 0, meas.wMid, meas.hMid, meas.width, meas.height)
        }),
        // 33 - symmetric, plus sign shape
        CROSS({ path, meas ->
            path.addRectangle(meas.w3, 0, meas.w32, meas.height)
            path.addRectangle(0, meas.h3, meas.width, meas.h32)
        }),
        // 34 - V-shape / arrow pointing down
        CHEVRON({ path, meas ->
            path.addPolygon(0, 0, meas.wMid, meas.hMid, meas.width, 0, meas.width, meas.h3)
            path.addPolygon(meas.wMid, meas.hMid, 0, meas.h3, meas.wMid, meas.height, meas.width, meas.h3)
        }),
        // 35 - L in corner
        L_SHAPE({ path, meas ->
            path.addRectangle(0, 0, meas.w3, meas.height)
            path.addRectangle(meas.w3, meas.h32, meas.width, meas.height)
        }),
        // 36 - two triangles meeting at center point
        HOURGLASS({ path, meas ->
            path.addTriangle(0, 0, meas.width, 0, meas.wMid, meas.hMid)
            path.addTriangle(0, meas.height, meas.width, meas.height, meas.wMid, meas.hMid)
        }),
        // 37 - staircase pattern (3 rectangles along diagonal)
        STEPPED_DIAGONAL({ path, meas ->
            path.addRectangle(0, 0, meas.w3, meas.h3)
            path.addRectangle(meas.w3, meas.h3, meas.w32, meas.h32)
            path.addRectangle(meas.w32, meas.h32, meas.width, meas.height)
        });

        private val tileDrawer = TileDrawer(drawer)

        fun draw(canvas: Canvas, measures: TileMeasures, rotation: Int, bgPaint: Paint, fgPaint: Paint) {
            tileDrawer.draw(canvas, measures, rotation, bgPaint, fgPaint)
        }
    }
}

