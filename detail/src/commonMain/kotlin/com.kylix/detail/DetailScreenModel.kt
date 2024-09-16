package com.kylix.detail

import beukmm.base.BaseScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.kylix.core.model.RecipeDetail
import com.kylix.core.repositories.favorite.FavoriteRepository
import com.kylix.core.repositories.recipe.RecipeRepository
import com.kylix.core.util.foldResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailScreenModel(
    private val recipeRepository: RecipeRepository,
    private val favoriteRepository: FavoriteRepository
) : BaseScreenModel() {

    var detailState = MutableStateFlow(DetailState())
        private set

    fun getRecipeDetail(
        recipeId: String
    ) {
        onSuspendProcess {
            recipeRepository.getRecipeDetail(recipeId).foldResult(
                onSuccess = { data ->
                    detailState.update {
                        it.copy(recipe = data, isFavorite = data.isFavorite)
                    }
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
}

data class DetailState(
    val recipe: RecipeDetail? = null,
    val isFavorite: Boolean = false
)