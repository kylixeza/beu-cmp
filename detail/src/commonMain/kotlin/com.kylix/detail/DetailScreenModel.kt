package com.kylix.detail

import beukmm.base.BaseScreenModel
import beukmm.util.TimerBenchmark
import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import com.kylix.core.model.RecipeDetail
import com.kylix.core.repositories.favorite.FavoriteRepository
import com.kylix.core.repositories.history.HistoryRepository
import com.kylix.core.repositories.recipe.RecipeRepository
import com.kylix.core.util.foldResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailScreenModel(
    private val recipeRepository: RecipeRepository,
    private val favoriteRepository: FavoriteRepository,
    private val historyRepository: HistoryRepository
) : BaseScreenModel() {

    var detailState = MutableStateFlow(DetailState())
        private set

    private val timer = TimerBenchmark()

    fun getRecipeDetail(
        recipeId: String
    ) {
        onSuspendProcess {
            recipeRepository.getRecipeDetail(recipeId).foldResult(
                onSuccess = { data ->
                    detailState.update {
                        it.copy(recipe = data, isFavorite = data.isFavorite)
                    }
                    timer.start()
                },
                onError = {
                    onDataError(it)
                }
            )
        }
    }

    fun toggleFavorite(recipeId: String) {

        screenModelScope.launch {
            if (detailState.value.isFavorite) {
                favoriteRepository.deleteFavoriteRecipe(recipeId).foldResult(
                    onSuccess = {
                        detailState.update {
                            it.copy(isFavorite = false)
                        }
                    },
                    onError = {
                        onDataError(it)
                    }
                )
            } else {
                favoriteRepository.postFavoriteRecipe(recipeId).foldResult(
                    onSuccess = {
                        detailState.update {
                            it.copy(isFavorite = true)
                        }
                    },
                    onError = {
                        onDataError(it)
                    }
                )
            }
        }
    }

    fun onVideoFinished() {
        timer.stop()
        val elapsedTime = timer.elapsedTime()
        Logger.i("Elapsed Time: $elapsedTime")
        detailState.update {
            it.copy(cookingSessionTime = timer.convertToMinutes(elapsedTime))
        }
        detailState.update {
            it.copy(showReviewSnacker = true)
        }
    }

    fun onCloseReviewSnacker() {
        detailState.update {
            it.copy(showReviewSnacker = false)
        }
    }

    fun postHistory(recipeId: String) {
        onSuspendProcess {
            val timeSpent = timer.elapsedTime()
            historyRepository.postHistory(recipeId, timeSpent).foldResult(
                onSuccess = { result ->
                    detailState.update {
                        it.copy(historyId = result)
                    }
                },
                onError = {
                    onDataError(it)
                }
            )
        }
    }
}

data class DetailState(
    val recipe: RecipeDetail? = null,
    val isFavorite: Boolean = false,
    val showReviewSnacker: Boolean = false,
    val cookingSessionTime: String = "",
    val historyId: String? = null
)