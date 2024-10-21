package com.kylix.profile.screens.help_center

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import beukmm.base.BaseScreenContent
import beukmm.components.BeuBasicTextField
import beukmm.components.SecondaryAppBar
import beukmm.di.koinScreenModel
import beukmm.theme.Primary500
import beukmm.theme.White
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import rememberStackedSnackbarHostState

class HelpCenterScreen: Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<HelpCenterScreenModel>()
        val helpCenterState by screenModel.helpCenterState.collectAsState()
        val uiState by screenModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        val stackedSnackbarHostState = rememberStackedSnackbarHostState()

        BaseScreenContent(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                SecondaryAppBar(
                    title = "Help Center",
                    onLeftIconClick = { navigator.pop() }
                )
            },
            stackedSnackbarHostState = stackedSnackbarHostState,
            uiState = uiState,
        ) { innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp, vertical = 12.dp),
            ) {
                Text(
                    text = "Write down you issue",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                BeuBasicTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = helpCenterState.message,
                    onValueChange = { screenModel.setMessage(it) },
                    label = "Please describe your issue",
                    minLines = 8,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { screenModel.sendEmail() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary500
                    ),
                    enabled = helpCenterState.message.isNotBlank()
                ) {
                    Text(
                        text = "Send",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = White
                        )
                    )
                }
            }
        }

        LaunchedEffect(uiState.isSuccess) {
            if (uiState.isSuccess) {
                stackedSnackbarHostState
                    .showSuccessSnackbar("Message sent successfully, please check your email")
            }
        }
    }

}