package com.lelloman.demo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lelloman.identicon.compose.ClassicIdenticon
import com.lelloman.identicon.compose.GithubIdenticon
import com.lelloman.identicon.util.toIdenticonHash
import com.lelloman.identicon.util.toSequentialBytes
import java.util.Random

enum class IdenticonVariant {
    RANDOM_CLASSIC,
    SEQUENCE_CLASSIC,
    GITHUB
}

@Composable
fun IdenticonGrid(
    variant: IdenticonVariant,
    onItemClick: (hash: Int, type: Int) -> Unit,
) {
    val random = remember { Random() }
    val sequenceOffset = remember { random.nextInt() }

    LazyVerticalGrid(columns = GridCells.Adaptive(64.dp)) {
        items(Int.MAX_VALUE) { index ->
            val hash = remember(index) {
                when (variant) {
                    IdenticonVariant.RANDOM_CLASSIC,
                    IdenticonVariant.GITHUB -> random.nextInt()
                    IdenticonVariant.SEQUENCE_CLASSIC -> index + sequenceOffset
                }
            }

            val hashBytes = remember(hash) {
                when (variant) {
                    IdenticonVariant.SEQUENCE_CLASSIC -> hash.toSequentialBytes()
                    else -> hash.toIdenticonHash()
                }
            }

            val type = when (variant) {
                IdenticonVariant.GITHUB -> TYPE_GITHUB
                else -> TYPE_CLASSIC
            }

            val modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clickable { onItemClick(hash, type) }

            when (variant) {
                IdenticonVariant.RANDOM_CLASSIC,
                IdenticonVariant.SEQUENCE_CLASSIC -> ClassicIdenticon(hash = hashBytes, modifier = modifier)
                IdenticonVariant.GITHUB -> GithubIdenticon(hash = hashBytes, modifier = modifier)
            }
        }
    }
}

const val TYPE_CLASSIC = 0
const val TYPE_GITHUB = 1
