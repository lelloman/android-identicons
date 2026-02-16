package com.lelloman.identicon.compose

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.asImageBitmap
import com.lelloman.identicon.drawable.ClassicIdenticonDrawable
import com.lelloman.identicon.drawable.GithubIdenticonDrawable
import com.lelloman.identicon.drawable.IdenticonDrawable

@Composable
fun ClassicIdenticon(hash: ByteArray, modifier: Modifier = Modifier) {
    val stableHash = remember(hash.contentHashCode()) { hash.copyOf() }
    IdenticonSpacer(
        modifier = modifier,
        factory = { w, h -> ClassicIdenticonDrawable(w, h, stableHash) },
    )
}

@Composable
fun GithubIdenticon(hash: ByteArray, modifier: Modifier = Modifier) {
    val stableHash = remember(hash.contentHashCode()) { hash.copyOf() }
    IdenticonSpacer(
        modifier = modifier,
        factory = { w, h -> GithubIdenticonDrawable(w, h, stableHash) },
    )
}

@Composable
private fun IdenticonSpacer(
    modifier: Modifier,
    factory: (Int, Int) -> IdenticonDrawable,
) {
    Spacer(
        modifier = modifier.drawWithCache {
            val w = size.width.toInt()
            val h = size.height.toInt()
            val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            val bitmapCanvas = Canvas(bitmap)
            factory(w, h).draw(bitmapCanvas)
            val imageBitmap = bitmap.asImageBitmap()
            onDrawBehind {
                drawImage(imageBitmap)
            }
        }
    )
}
