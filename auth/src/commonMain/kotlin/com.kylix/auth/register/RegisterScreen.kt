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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import beukmm.theme.Black
import beukmm.theme.Primary500
import beukmm.theme.White
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

        val screenModel = koinScreenModel<RegisterScreenModel>()
        val registerState = screenModel.registerState.collectAsState().value
        val uiState = screenModel.uiState.collectAsState().value

        BaseScreenContent(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            topBar = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { navigator.pop() }
                    ) {
                        Icon(
                            imageVector = FeatherIcons.ArrowLeft,
                            tint = Black,
                            contentDescription = "Back"
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Register",
                        style = MaterialTheme.typography.h4.copy(
                            fontSize = 22.sp
                        )
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                BeuBasicTextField(
                    value = registerState.username,
                    onValueChange = { screenModel.onUsernameChanged(it) },
                    label = "Username",
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                BeuBasicTextField(
                    value = registerState.name,
                    onValueChange = { screenModel.onNameChanged(it) },
                    label = "Name",
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                BeuBasicTextField(
                    value = registerState.email,
                    onValueChange = { screenModel.onEmailChanged(it) },
                    label = "Email",
                    singleLine = true
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
                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {  },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Primary500
                    ),
                    contentPadding = PaddingValues(8.dp),
                ) {
                    Text(
                        text = "Register",
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.body1.copy(
                            color = White
                        ),
                    )
                }
            }
        }
    }
}