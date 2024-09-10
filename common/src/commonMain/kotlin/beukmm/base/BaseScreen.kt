package beukmm.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import beukmm.components.LoadingDialog
import cafe.adriel.voyager.core.screen.Screen

open class BaseScreen : Screen {

    open var screenModel: BaseScreenModel? = null

    @Composable
    override fun Content() {

        val uiState = screenModel?.uiState?.collectAsState()

        if (uiState?.value?.isLoading == true) {
            LoadingContent()
        }

        ScreenContent(uiState)
    }

    @Composable
    open fun LoadingContent() {
        LoadingDialog(
            onDismissRequest = {
                screenModel?.onFinishLoading()
            }
        )
    }

    @Composable
    open fun ScreenContent(
        uiState: State<BaseUIState?>?
    ) {}
}