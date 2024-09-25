package com.kylix.camera

import beukmm.base.BaseScreenModel
import com.kylix.core.model.RecipeList
import com.kylix.core.repositories.recipe.RecipeRepository
import com.kylix.core.util.foldResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class CameraScreenModel(
    private val repository: RecipeRepository
): BaseScreenModel() {

    var cameraState = MutableStateFlow(CameraState())
        private set

    fun allPermissionGranted() {
        cameraState.update {
            it.copy(
                cameraPermissionGranted = true,
                storagePermissionGranted = true
            )
        }
    }

    fun grantCameraPermission() {
        cameraState.update {
            it.copy(cameraPermissionGranted = true)
        }
    }

    fun grantStoragePermission() {
        cameraState.update {
            it.copy(storagePermissionGranted = true)
        }
    }

    fun setImageResult(imageResult: ByteArray) {
        cameraState.update {
            it.copy(imageResult = imageResult)
        }
    }

    fun setPredictionResult(predictionResult: String) {
        cameraState.update {
            it.copy(predictionResult = predictionResult)
        }
    }

    fun getRelatedRecipes() {
        onSuspendProcess {
            val query = cameraState.value.predictionResult
            val response = repository.getRelatedPredictionRecipes(query)
            response.foldResult(
                onSuccess = { result ->
                    cameraState.update {
                        it.copy(recipes = result)
                    }
                },
                onError = {
                    onDataError(it)
                }
            )
        }
    }
}

data class CameraState(
    val cameraPermissionGranted: Boolean = false,
    val storagePermissionGranted: Boolean = false,
    val imageResult: ByteArray = byteArrayOf(),
    val predictionResult: String = "",
    val recipes: List<RecipeList> = emptyList(),
)