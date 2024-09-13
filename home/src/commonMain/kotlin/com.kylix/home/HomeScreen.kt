package com.kylix.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import beukmm.navigator.SharedScreen
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

class HomeScreen : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.current
        val loginScreen = rememberScreen(SharedScreen.Register)

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Home Screen")
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    navigator?.push(loginScreen)
                },
            ) {
                Text("Back To Splash")
            }
        }
    }
}