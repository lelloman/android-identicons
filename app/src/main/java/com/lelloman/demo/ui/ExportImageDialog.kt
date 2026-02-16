package com.lelloman.demo.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.lelloman.identicon.drawable.ClassicIdenticonDrawable
import com.lelloman.identicon.drawable.GithubIdenticonDrawable
import com.lelloman.identicon.util.toIdenticonHash
import java.io.File
import java.io.FileOutputStream

private val SIZES = intArrayOf(32, 64, 128, 256, 512, 1024, 2048)

@Composable
fun ExportImageDialog(
    onDismiss: () -> Unit,
    onExport: (Uri) -> Unit,
    hash: Int,
    type: Int,
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select size") },
        text = {
            Column {
                SIZES.forEachIndexed { index, size ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedIndex = index }
                            .padding(vertical = 4.dp),
                    ) {
                        RadioButton(
                            selected = selectedIndex == index,
                            onClick = { selectedIndex = index },
                        )
                        Text(
                            text = "${size}x${size}",
                            modifier = Modifier.padding(start = 8.dp),
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val uri = exportBitmap(context, SIZES[selectedIndex], hash, type)
                if (uri != null) {
                    onExport(uri)
                }
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
    )
}

private fun exportBitmap(context: Context, size: Int, hash: Int, type: Int): Uri? {
    val hashBytes = hash.toIdenticonHash()
    val drawable = when (type) {
        TYPE_CLASSIC -> ClassicIdenticonDrawable(size, size, hashBytes)
        TYPE_GITHUB -> GithubIdenticonDrawable(size, size, hashBytes)
        else -> return null
    }

    val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
    drawable.draw(Canvas(bitmap))

    val filename = "${System.currentTimeMillis()}.png"
    val file = File(context.cacheDir, filename)
    return try {
        FileOutputStream(file).use { fos ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
        }
        FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
