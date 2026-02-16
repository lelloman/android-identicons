package com.lelloman.demo.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lelloman.demo.ui.AppTheme
import com.lelloman.demo.ui.ExportImageDialog
import com.lelloman.demo.ui.TYPE_CLASSIC
import com.lelloman.identicon.compose.ClassicIdenticon
import com.lelloman.identicon.compose.GithubIdenticon
import com.lelloman.identicon.util.toIdenticonHash
import java.util.Random

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val type = intent?.getIntExtra(EXTRA_IDENTICON_TYPE, TYPE_CLASSIC) ?: TYPE_CLASSIC
        val hash = intent?.getIntExtra(EXTRA_HASH, Random().nextInt()) ?: Random().nextInt()

        setContent {
            AppTheme {
                DetailScreen(
                    hash = hash,
                    type = type,
                    onExport = { uri -> shareImage(uri) },
                )
            }
        }
    }

    private fun shareImage(uri: Uri) {
        val shareIntent = Intent(Intent.ACTION_SEND)
            .setType("image/png")
            .putExtra(Intent.EXTRA_STREAM, uri)
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(Intent.createChooser(shareIntent, "Share Image"))
    }

    companion object {
        const val EXTRA_IDENTICON_TYPE = "type"
        const val EXTRA_HASH = "hash"
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailScreen(
    hash: Int,
    type: Int,
    onExport: ((Uri) -> Unit)? = null,
) {
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("hash: $hash") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                actions = {
                    IconButton(onClick = { showDialog = true }) {
                        Icon(
                            imageVector = Icons.Filled.FileDownload,
                            contentDescription = "Export",
                        )
                    }
                },
            )
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(32.dp),
            contentAlignment = Alignment.Center,
        ) {
            val hashBytes = remember { hash.toIdenticonHash() }
            val modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
            when (type) {
                TYPE_CLASSIC -> ClassicIdenticon(hash = hashBytes, modifier = modifier)
                else -> GithubIdenticon(hash = hashBytes, modifier = modifier)
            }
        }

        if (showDialog) {
            ExportImageDialog(
                onDismiss = { showDialog = false },
                onExport = { uri -> onExport?.invoke(uri) },
                hash = hash,
                type = type,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    AppTheme {
        DetailScreen(
            hash = 12345,
            type = TYPE_CLASSIC,
        )
    }
}
