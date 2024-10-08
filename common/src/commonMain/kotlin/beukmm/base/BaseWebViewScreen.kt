package beukmm.base

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import beukmm.components.BaseAppBar
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState

abstract class BaseWebViewScreen(
    private val title: String,
    private val url: String
): Screen {

    @Composable
    override fun Content() {

        val state = rememberWebViewState(url)

        val navigator = LocalNavigator.currentOrThrow

        BaseScreenContent(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                BaseAppBar(
                    title = title,
                    onLeftIconClick = { navigator.pop() },
                    rightIcon = null
                )
            }
        ) { innerPadding ->
            WebView(
                state = state,
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            )
        }
    }

}