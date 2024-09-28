package com.kylix.camera

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import beukmm.base.BaseScreenContent
import beukmm.camera.generated.resources.Res
import beukmm.camera.generated.resources.ic_camera_capture
import beukmm.di.koinScreenModel
import beukmm.navigator.SharedScreen
import beukmm.theme.Primary500
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kashif.cameraK.CameraController
import com.kashif.cameraK.CameraKPreview
import com.kashif.cameraK.ImageCaptureResult
import com.kashif.cameraK.ImageFormat
import com.kashif.cameraK.RequestCameraPermission
import com.kashif.cameraK.RequestStoragePermission
import com.kylix.camera.components.PredictionResultBottomSheet
import com.kylix.camera.tflite.TFLiteHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

class CameraScreen: Screen {

    @Composable
    override fun Content() {
        val cameraController = remember { CameraController() }
        val tfLiteHelper = koinInject<TFLiteHelper>()

        val coroutineScope = rememberCoroutineScope()
        val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

        val screenModel = koinScreenModel<CameraScreenModel>()
        val cameraState by screenModel.cameraState.collectAsState()
        val uiState by screenModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            if(cameraController.allPermissionsGranted()) {
                screenModel.allPermissionGranted()
            }
        }

        if (cameraState.cameraPermissionGranted.not()) {
            RequestCameraPermission(
                onGranted = { screenModel.grantCameraPermission() },
                onDenied = { }
            )
        }

        if (cameraState.storagePermissionGranted.not()) {
            RequestStoragePermission(
                onGranted = { screenModel.grantStoragePermission() },
                onDenied = { }
            )
        }

        if (cameraState.cameraPermissionGranted && cameraState.storagePermissionGranted) {
            cameraController.bindCamera()
        }

        PredictionResultBottomSheet(
            result = cameraState.predictionResult,
            recipes = cameraState.recipes,
            sheetState = bottomSheetState,
            onItemSelected = { recipeId ->
                navigator.push(ScreenRegistry.get(SharedScreen.Detail(recipeId)))
            }
        ) {
            BaseScreenContent(
                modifier = Modifier.fillMaxSize(),
                uiState = uiState,
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CameraKPreview(
                        modifier = Modifier.fillMaxSize(),
                        cameraController = cameraController,
                    )

                    IconButton(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 92.dp),
                        onClick = {
                            coroutineScope.launch {
                                when (val result = cameraController.takePicture(ImageFormat.JPEG)) {
                                    is ImageCaptureResult.Error -> {  }
                                    is ImageCaptureResult.Success -> {
                                        screenModel.setImageResult(result.image)
                                    }
                                }
                            }
                        }
                    ) {
                        Icon(
                            modifier = Modifier,
                            painter = painterResource(Res.drawable.ic_camera_capture),
                            contentDescription = null,
                            tint = Primary500
                        )
                    }
                }
            }
        }

        LaunchedEffect(cameraState.imageResult) {
            if (cameraState.imageResult.isNotEmpty()) {
                coroutineScope.launch {
                    val predictionResult = tfLiteHelper.classifyImage(cameraState.imageResult)
                    screenModel.setPredictionResult(predictionResult)
                    delay(1000)

                    screenModel.getRelatedRecipes()
                    bottomSheetState.show()
                }
            }
        }
    }
}