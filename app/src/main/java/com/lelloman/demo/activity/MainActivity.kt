package com.lelloman.demo.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.lelloman.demo.ui.AppTheme
import com.lelloman.demo.ui.ClassicTilesGrid
import com.lelloman.demo.ui.IdenticonGrid
import com.lelloman.demo.ui.IdenticonVariant
import com.lelloman.demo.ui.TYPE_CLASSIC
import com.lelloman.demo.ui.TYPE_GITHUB
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tabTitles = listOf("Random", "Sequence", "Tiles", "Github")

        setContent {
            AppTheme {
                val pagerState = rememberPagerState { tabTitles.size }
                val scope = rememberCoroutineScope()

                Column(modifier = Modifier.fillMaxSize()) {
                    TopAppBar(
                        title = { Text("Identicons Demo") },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                            titleContentColor = androidx.compose.material3.MaterialTheme.colorScheme.onPrimary,
                        ),
                    )
                    TabRow(
                        selectedTabIndex = pagerState.currentPage,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        tabTitles.forEachIndexed { index, title ->
                            Tab(
                                selected = pagerState.currentPage == index,
                                onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                                text = { Text(title) },
                            )
                        }
                    }
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                    ) { page ->
                        val onItemClick = { hash: Int, type: Int ->
                            val intent = Intent(this@MainActivity, DetailActivity::class.java)
                                .putExtra(DetailActivity.EXTRA_HASH, hash)
                                .putExtra(DetailActivity.EXTRA_IDENTICON_TYPE, type)
                            startActivity(intent)
                        }
                        when (page) {
                            0 -> IdenticonGrid(IdenticonVariant.RANDOM_CLASSIC, onItemClick)
                            1 -> IdenticonGrid(IdenticonVariant.SEQUENCE_CLASSIC, onItemClick)
                            2 -> ClassicTilesGrid()
                            3 -> IdenticonGrid(IdenticonVariant.GITHUB, onItemClick)
                        }
                    }
                }
            }
        }
    }
}
