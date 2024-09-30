package com.kylix.camera

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
import androidx.compose.runtime.rememberCoroutineScope
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
import com.kylix.camera.components.PredictionResultBottomSheet
import com.kylix.camera.tflite.TFLiteHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

class CameraScreen: Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val tfLiteHelper = koinInject<TFLiteHelper>()

        val coroutineScope = rememberCoroutineScope()
        val bottomSheetState = rememberModalBottomSheetState()

        val screenModel = koinScreenModel<CameraScreenModel>()
        val cameraState by screenModel.cameraState.collectAsState()
        val uiState by screenModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        BaseScreenContent(
            modifier = Modifier.fillMaxSize(),
            uiState = uiState
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize()) {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(80.dp)
                        .size(128.dp),
                    onClick = {
                        coroutineScope.launch {

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
}
