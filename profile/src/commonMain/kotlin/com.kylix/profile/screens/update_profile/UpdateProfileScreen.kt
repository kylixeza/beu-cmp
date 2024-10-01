package com.kylix.profile.screens.update_profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import beukmm.base.BaseScreenContent
import beukmm.components.BeuBasicTextField
import beukmm.components.SecondaryAppBar
import beukmm.di.koinScreenModel
import beukmm.profile.generated.resources.Res
import beukmm.profile.generated.resources.ic_edit
import beukmm.theme.White
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.multiplatform.lifecycle.LocalLifecycleTracker
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap
import org.jetbrains.compose.resources.painterResource

class UpdateProfileScreen: Screen {

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {

        val screenModel = koinScreenModel<UpdateProfileScreenModel>()
        val updateProfileState by screenModel.updateProfileState.collectAsState()
        val uiState by screenModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        val lifecycleTracker = LocalLifecycleTracker.current

        val scope = rememberCoroutineScope()
        val singleImagePicker = rememberImagePickerLauncher(
            selectionMode = SelectionMode.Single,
            scope = scope,
            onResult = { byteArrays ->
                byteArrays.firstOrNull()?.let {
                    screenModel.setNewAvatar(it)
                }
            }
        )

        BaseScreenContent(
            topBar = {
                SecondaryAppBar(
                    title = "Update Profile",
                    onLeftIconClick = { navigator.pop() }
                )
            },
            uiState = uiState,
            onLoadingDialogDismissRequest = { screenModel.onFinishLoading() }
        ) { innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.size(100.dp)
                ) {
                    if (updateProfileState.newAvatar == null) {
                        val painter = asyncPainterResource(updateProfileState.user?.avatar ?: "")

                        KamelImage(
                            resource = painter,
                            contentDescription = "Avatar",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(100.dp).clip(shape = CircleShape)
                        )
                    } else {
                        updateProfileState.newAvatar?.decodeToImageBitmap()?.let {
                            Image(
                                bitmap = it,
                                contentDescription = "Avatar",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(100.dp).clip(shape = CircleShape)
                            )
                        }
                    }


                    Image(
                        painter = painterResource(Res.drawable.ic_edit),
                        contentDescription = "Edit",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(36.dp).align(Alignment.BottomEnd).clickable {
                            singleImagePicker.launch()
                        }
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                BeuBasicTextField(
                    value = updateProfileState.username,
                    label = "Username",
                    onValueChange = { screenModel.setUsername(it) },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))
                BeuBasicTextField(
                    value = updateProfileState.email,
                    label = "Email",
                    onValueChange = { screenModel.setEmail(it) },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { screenModel.updateProfile() },
                    enabled = updateProfileState.username.isNotBlank() && updateProfileState.email.isNotBlank()
                ) {
                    Text(
                        text = "Update Profile",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = White
                        )
                    )
                }
            }
        }

        LaunchedEffect(key1 = uiState.isSuccess) {
            if (uiState.isSuccess) {
                navigator.pop()
            }
        }
    }
}