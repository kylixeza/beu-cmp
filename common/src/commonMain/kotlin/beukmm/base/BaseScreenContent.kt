package beukmm.base

import StackedSnackbarHost
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.DrawerDefaults
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.contentColorFor
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import beukmm.components.LoadingDialog
import rememberStackedSnackbarHostState

@Composable
fun BaseScreenContent(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    isFloatingActionButtonDocked: Boolean = false,
    drawerContent: @Composable (ColumnScope.() -> Unit)? = null,
    drawerGesturesEnabled: Boolean = true,
    drawerShape: Shape = MaterialTheme.shapes.large,
    drawerElevation: Dp = DrawerDefaults.Elevation,
    drawerBackgroundColor: Color = MaterialTheme.colors.surface,
    drawerContentColor: Color = contentColorFor(drawerBackgroundColor),
    drawerScrimColor: Color = DrawerDefaults.scrimColor,
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = contentColorFor(backgroundColor),
    uiState: BaseUIState? = null,
    onLoadingDialogDismissRequest: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {

    val stackedSnackbarHostState = rememberStackedSnackbarHostState()

    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = {
            StackedSnackbarHost(hostState = stackedSnackbarHostState)
        },
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        isFloatingActionButtonDocked = isFloatingActionButtonDocked,
        drawerContent = drawerContent,
        drawerGesturesEnabled = drawerGesturesEnabled,
        drawerShape = drawerShape,
        drawerElevation = drawerElevation,
        drawerBackgroundColor = drawerBackgroundColor,
        drawerContentColor = drawerContentColor,
        drawerScrimColor = drawerScrimColor,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
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