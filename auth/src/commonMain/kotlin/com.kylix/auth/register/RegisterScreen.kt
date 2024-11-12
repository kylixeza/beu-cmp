package com.kylix.auth.register

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import beukmm.components.PasswordTerms
import beukmm.components.SecondaryAppBar
import beukmm.di.koinScreenModel
import beukmm.navigator.SharedScreen
import beukmm.theme.Black
import beukmm.theme.Primary500
import beukmm.theme.White
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft
import compose.icons.feathericons.Eye
import compose.icons.feathericons.EyeOff

class RegisterScreen : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val mainScreen = rememberScreen(SharedScreen.Main)

        val screenModel = koinScreenModel<RegisterScreenModel>()
        val registerState = screenModel.registerState.collectAsState().value
        val uiState = screenModel.uiState.collectAsState().value

        BaseScreenContent(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                SecondaryAppBar(
                    title = "Register",
                    onLeftIconClick = { navigator.pop() }
                )
            },
            uiState = uiState,
            onLoadingDialogDismissRequest = { screenModel.onFinishLoading() }
        ) { innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.Top,
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                BeuBasicTextField(
                    value = registerState.username,
                    onValueChange = { screenModel.onUsernameChanged(it) },
                    label = "Username",
                    singleLine = true,
                    validator = { registerState.isUsernameValid.first },
                )
                Spacer(modifier = Modifier.height(8.dp))
                BeuBasicTextField(
                    value = registerState.email,
                    onValueChange = { screenModel.onEmailChanged(it) },
                    label = "Email",
                    singleLine = true,
                    validator = { registerState.isEmailValid.first }
                )
                Spacer(modifier = Modifier.height(8.dp))
                BeuBasicTextField(
                    value = registerState.password,
                    onValueChange = { screenModel.onPasswordChanged(it) },
                    label = "Password",
                    singleLine = true,
                    visualTransformation = if (registerState.isPasswordVisible) VisualTransformation.None
                        else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = { screenModel.onPasswordVisibilityChanged() }
                        ) {
                            Icon(
                                imageVector = if (registerState.isPasswordVisible) FeatherIcons.EyeOff else FeatherIcons.Eye,
                                contentDescription = if (registerState.isPasswordVisible) "Hide password" else "Show password",
                                tint = Black
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                PasswordTerms(
                    password = registerState.password,
                )
                Spacer(modifier = Modifier.height(8.dp))
                BeuBasicTextField(
                    value = registerState.confirmPassword,
                    onValueChange = { screenModel.onConfirmPasswordChanged(it) },
                    label = "Confirm Password",
                    singleLine = true,
                    visualTransformation = if (registerState.isConfirmPasswordVisible) VisualTransformation.None
                        else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = { screenModel.onConfirmPasswordVisibilityChanged() }
                        ) {
                            Icon(
                                imageVector = if (registerState.isConfirmPasswordVisible) FeatherIcons.EyeOff else FeatherIcons.Eye,
                                contentDescription = if (registerState.isConfirmPasswordVisible) "Hide password" else "Show password",
                                tint = Black
                            )
                        }
                    },
                    validator = { registerState.isConfirmPasswordValid.first }
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { screenModel.register() },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary500
                    ),
                    contentPadding = PaddingValues(8.dp),
                    enabled = registerState.isUsernameValid.second
                            && registerState.isEmailValid.second
                            && registerState.isPasswordValid.second
                            && registerState.isConfirmPasswordValid.second
                ) {
                    Text(
                        text = "Register",
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = White
                        ),
                    )
                }
            }
        }

        LaunchedEffect(uiState.isSuccess) {
            if (uiState.isSuccess) navigator.replaceAll(mainScreen)
        }
    }
}