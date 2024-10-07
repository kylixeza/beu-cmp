package com.kylix.reset_password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import beukmm.base.BaseScreenContent
import beukmm.components.BeuBasicTextField
import beukmm.components.SecondaryAppBar
import beukmm.di.koinScreenModel
import beukmm.theme.Black
import beukmm.theme.White
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kylix.reset_password.components.PasswordConstraintItem
import compose.icons.FeatherIcons
import compose.icons.feathericons.Eye
import compose.icons.feathericons.EyeOff
import rememberStackedSnackbarHostState

class ResetPasswordScreen: Screen {

    @Composable
    override fun Content() {

        val screenModel = koinScreenModel<ResetPasswordScreenModel>()
        val resetPasswordState by screenModel.resetPasswordState.collectAsState()
        val uiState by screenModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        BaseScreenContent(
            topBar = {
                SecondaryAppBar(
                    title = "Reset Password",
                    onLeftIconClick = { navigator.pop() }
                )
            },
            uiState = uiState,
            onLoadingDialogDismissRequest = { screenModel.onFinishLoading() },
        ) { innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                    .padding(innerPadding)
            ) {
                BeuBasicTextField(
                    value = resetPasswordState.password,
                    onValueChange = { screenModel.setPassword(it) },
                    label = "Password",
                    visualTransformation = if (resetPasswordState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = { screenModel.togglePasswordVisibility() }
                        ) {
                            Icon(
                                imageVector = if (resetPasswordState.isPasswordVisible) FeatherIcons.EyeOff else FeatherIcons.Eye,
                                contentDescription = if (resetPasswordState.isPasswordVisible) "Hide password" else "Show password",
                                tint = Black
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                BeuBasicTextField(
                    value = resetPasswordState.confirmPassword,
                    onValueChange = { screenModel.setConfirmPassword(it) },
                    label = "Confirm Password",
                    visualTransformation = if (resetPasswordState.isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = { screenModel.toggleConfirmPasswordVisibility() }
                        ) {
                            Icon(
                                imageVector = if (resetPasswordState.isConfirmPasswordVisible) FeatherIcons.EyeOff else FeatherIcons.Eye,
                                contentDescription = if (resetPasswordState.isConfirmPasswordVisible) "Hide password" else "Show password",
                                tint = Black
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Password must be fulfilled with:",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(resetPasswordState.passwordTerms) {
                        PasswordConstraintItem(
                            isCorrect = it.isFulfilled,
                            terms = it.description
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = { screenModel.resetPassword() },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = resetPasswordState.password.isNotEmpty()
                            && resetPasswordState.confirmPassword.isNotEmpty()
                            && resetPasswordState.password == resetPasswordState.confirmPassword
                            && resetPasswordState.passwordTerms.all { it.isFulfilled }
                ) {
                    Text(
                        "Reset Password",
                        style = MaterialTheme.typography.bodyMedium.copy(color = White)
                    )
                }
            }
        }

        LaunchedEffect(uiState.isSuccess) {
            if (uiState.isSuccess) { navigator.pop() }
        }
    }
}