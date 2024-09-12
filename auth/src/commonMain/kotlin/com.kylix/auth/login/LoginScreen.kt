package com.kylix.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import beukmm.base.BaseScreenContent
import beukmm.components.BeuBasicTextField
import beukmm.di.koinScreenModel
import beukmm.navigator.SharedScreen
import beukmm.theme.Black
import beukmm.theme.Primary500
import beukmm.theme.White
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import compose.icons.FeatherIcons
import compose.icons.feathericons.Eye
import compose.icons.feathericons.EyeOff

class LoginScreen: Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val mainScreen = rememberScreen(SharedScreen.Main)

        val screenModel = koinScreenModel<LoginScreenModel>()
        val loginState = screenModel.loginState.collectAsState().value
        val uiState = screenModel.uiState.collectAsState().value

        val registerScreen = rememberScreen(SharedScreen.Register)

        BaseScreenContent(
            uiState = uiState,
            onLoadingDialogDismissRequest = { screenModel.onFinishLoading() }
        ) { innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp).padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.h4.copy(
                        fontSize = 22.sp
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                BeuBasicTextField(
                    value = loginState.identifier,
                    onValueChange = { screenModel.onIdentifierChanged(it) },
                    label = "Email / Username",
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))

                BeuBasicTextField(
                    value = loginState.password,
                    onValueChange = { screenModel.onPasswordChanged(it) },
                    label = "Password",
                    singleLine = true,
                    visualTransformation = if (loginState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = { screenModel.onPasswordVisibilityChanged() }
                        ) {
                            Icon(
                                imageVector = if (loginState.isPasswordVisible) FeatherIcons.EyeOff else FeatherIcons.Eye,
                                contentDescription = if (loginState.isPasswordVisible) "Hide password" else "Show password",
                                tint = Black
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { screenModel.login() },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Primary500
                    ),
                    contentPadding = PaddingValues(8.dp),
                    enabled = loginState.identifier.isNotBlank() && loginState.password.isNotBlank()
                ) {
                    Text(
                        text = "Login",
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.body1.copy(
                            color = White
                        ),
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Don't have an account? ")
                    Text(
                        text = "Register",
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.clickable {
                            navigator.push(registerScreen)
                        }
                    )
                }
            }
        }

        LaunchedEffect(uiState.isSuccess) {
            if (uiState.isSuccess) {
                navigator.replace(mainScreen)
            }
        }
    }
}