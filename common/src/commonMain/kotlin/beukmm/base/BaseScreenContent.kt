package beukmm.base

import StackedSnackbarHost
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import beukmm.components.LoadingDialog
import beukmm.components.PlatformColors
import beukmm.theme.White
import rememberStackedSnackbarHostState

@Composable
fun BaseScreenContent(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    contentColor: Color = White,
    containerColor: Color = White,
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    uiState: BaseUIState? = null,
    onLoadingDialogDismissRequest: () -> Unit = {},
    statusBarColor: Color = Color.Transparent,
    navBarColor: Color = Color.Transparent,
    content: @Composable (PaddingValues) -> Unit
) {

    val stackedSnackbarHostState = rememberStackedSnackbarHostState()

    PlatformColors(statusBarColor, navBarColor)

    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = {
            StackedSnackbarHost(hostState = stackedSnackbarHostState)
        },
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        contentColor = contentColor,
        containerColor = containerColor,
        contentWindowInsets = contentWindowInsets
    ) {
        content(it)
    }

    if (uiState?.isLoading == true) {
        LoadingDialog(
            onDismissRequest = onLoadingDialogDismissRequest
        )
    }

    LaunchedEffect(
       key1 = uiState?.isError
    ) {
        if (uiState?.isError == true) {
            stackedSnackbarHostState.showErrorSnackbar(
                title = "Something went wrong",
                description = uiState.errorMessage,
                duration = StackedSnackbarDuration.Short
            )
        }
    }

}