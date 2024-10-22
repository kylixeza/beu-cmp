package com.kylix.camera

import StackedSnakbarHostState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.rememberModalBottomSheetState
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
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kashif.cameraK.controller.CameraController
import com.kashif.cameraK.enums.CameraLens
import com.kashif.cameraK.enums.Directory
import com.kashif.cameraK.enums.FlashMode
import com.kashif.cameraK.enums.ImageFormat
import com.kashif.cameraK.permissions.providePermissions
import com.kashif.cameraK.result.ImageCaptureResult
import com.kashif.cameraK.ui.CameraPreview
import com.kylix.camera.components.PredictionResultBottomSheet
import com.kylix.camera.tflite.TFLiteHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import rememberStackedSnackbarHostState

class CameraScreen: Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val tfLiteHelper = koinInject<TFLiteHelper>()
        var cameraController by remember { mutableStateOf<CameraController?>(null) }

        val coroutineScope = rememberCoroutineScope()
        val bottomSheetState = rememberModalBottomSheetState()
        val stackedSnackbarState = rememberStackedSnackbarHostState()

        val screenModel = koinScreenModel<CameraScreenModel>()
        val cameraState by screenModel.cameraState.collectAsState()
        val uiState by screenModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        RequestPermissions(screenModel)

        BaseScreenContent(
            modifier = Modifier.fillMaxSize(),
            uiState = uiState,
            stackedSnackbarHostState = stackedSnackbarState,
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize()) {
                if (cameraState.cameraPermissionGranted && cameraState.storagePermissionGranted) {
                    CameraPreview(
                        modifier = Modifier.fillMaxSize(),
                        cameraConfiguration = {
                            setCameraLens(CameraLens.BACK)
                            setFlashMode(FlashMode.AUTO)
                            setImageFormat(ImageFormat.JPEG)
                            setDirectory(Directory.DCIM)
                    }, onCameraControllerReady = {
                        cameraController = it
                    })
                }
                IconButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(80.dp)
                        .size(128.dp),
                    onClick = {
                        coroutineScope.launch {
                            takePicture(cameraController, stackedSnackbarState, screenModel)
                        }
                    }
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_camera_capture),
                        contentDescription = null,
                    )
                }
            }
        }

        if (cameraState.showBottomSheet) {
            PredictionResultBottomSheet(
                result = cameraState.predictionResult,
                recipes = cameraState.recipes,
                sheetState = bottomSheetState,
                onItemSelected = { recipeId ->
                    navigator.push(ScreenRegistry.get(SharedScreen.Detail(recipeId)))
                },
                onDismissRequest = { screenModel.hideBottomSheet() }
            )
        }

        LaunchedEffect(cameraState.imageResult) {
            if (cameraState.imageResult.isNotEmpty()) {
                coroutineScope.launch {
                    val predictionResult = tfLiteHelper.classifyImage(cameraState.imageResult)
                    screenModel.setPredictionResult(predictionResult)
                    delay(1000)

                    screenModel.showBottomSheet()
                    screenModel.getRelatedRecipes()
                }
            }
        }
    }

    private suspend fun takePicture(
        cameraController: CameraController?,
        stackedSnackbar: StackedSnakbarHostState,
        screenModel: CameraScreenModel
    ) {
        if (cameraController != null) {
            screenModel.onStartLoading()
            when (val result = cameraController.takePicture()) {
                is ImageCaptureResult.Success -> screenModel.setImageResult(result.byteArray)
                is ImageCaptureResult.Error -> stackedSnackbar.showErrorSnackbar("Cannot take picture")
            }
        }
    }

    @Composable
    private fun RequestPermissions(
        screenModel: CameraScreenModel,
    ) {
        val permissions = providePermissions()
        val hasCameraPermission by remember { mutableStateOf(permissions.hasCameraPermission()) }
        val hasStoragePermission by remember { mutableStateOf(permissions.hasStoragePermission()) }

        if (hasCameraPermission) {
            screenModel.grantCameraPermission()
        } else {
            permissions.RequestStoragePermission(
                onGranted = { screenModel.grantCameraPermission() },
                onDenied = { }
            )
        }

        if (hasStoragePermission) {
            screenModel.grantStoragePermission()
        } else {
            permissions.RequestStoragePermission(
                onGranted = { screenModel.grantStoragePermission() },
                onDenied = { }
            )
        }
    }
}
