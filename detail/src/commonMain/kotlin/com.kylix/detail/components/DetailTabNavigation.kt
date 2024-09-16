package com.kylix.detail.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
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

@OptIn(ExperimentalFoundationApi::class)
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
                backgroundColor = White,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
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
                                style = MaterialTheme.typography.body2
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