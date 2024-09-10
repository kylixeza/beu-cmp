package com.kylix.beukmm

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import beukmm.theme.BeuTheme
import com.kylix.navigation.AppNavigator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    BeuTheme {
        AppNavigator()
    }
}