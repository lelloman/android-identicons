package com.lelloman.demo.ui

import android.graphics.Paint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lelloman.identicon.drawable.ClassicIdenticonTile
import com.lelloman.identicon.util.TileMeasures

@Composable
fun ClassicTilesGrid() {
    val tiles = remember { ClassicIdenticonTile.all }

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.fillMaxWidth(),
    ) {
        itemsIndexed(tiles) { index, tile ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(4.dp),
            ) {
                TileCell(tile = tile)
                val suffix = if (index % 4 == 0) "*" else ""
                Text(
                    text = "$index$suffix",
                    fontSize = 10.sp,
                )
            }
        }
    }
}

@Composable
private fun TileCell(tile: ClassicIdenticonTile.Tiles) {
    val whitePaint = remember {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = 0xFFFFFFFF.toInt()
            style = Paint.Style.FILL
        }
    }
    val blackPaint = remember {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = 0xFF000000.toInt()
            style = Paint.Style.FILL
        }
    }

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .drawWithCache {
                val measures = TileMeasures(size.width.toInt(), size.height.toInt())
                onDrawBehind {
                    drawRect(Color(0xFF999999))
                    drawIntoCanvas { canvas ->
                        tile.draw(canvas.nativeCanvas, measures, 0, whitePaint, blackPaint)
                    }
                }
            }
    )
}
