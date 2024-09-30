package com.kylix.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import beukmm.theme.Neutral900
import beukmm.theme.Primary700
import beukmm.theme.White
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.kylix.detail.DetailState
import com.kylix.detail.tabs.AboutTab
import com.kylix.detail.tabs.InstructionTab
import com.kylix.detail.tabs.ReviewTab

@Composable
fun DetailTabNavigation(
    detailState: DetailState
) {

    val tabs = remember { listOf(InstructionTab(detailState), AboutTab(detailState), ReviewTab(detailState)) }
    TabNavigator(tabs[0]) { tabNavigator ->
        val pagerState = rememberPagerState { tabs.size }

        LaunchedEffect(tabNavigator.current) {
            pagerState.animateScrollToPage(tabs.indexOf(tabNavigator.current))
        }

        LaunchedEffect(pagerState.currentPage) {
            tabNavigator.current = tabs[pagerState.currentPage]
        }

        Column {
            TabRow(
                selectedTabIndex = tabs.indexOf(tabNavigator.current),
                containerColor = White,
                indicator = { tabPositions ->
                    SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[tabs.indexOf(tabNavigator.current)]),
                        color = Primary700
                    )
                }
            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = tabNavigator.current == tab,
                        onClick = { tabNavigator.current = tab },
                        text = {
                            Text(
                                text = tab.options.title,
                                color = if (tabNavigator.current == tab) Primary700 else Neutral900,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                tabs[page].Content()
            }
        }
    }

}