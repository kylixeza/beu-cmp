package com.kylix.profile.screens.favorite

import beukmm.base.BaseScreenModel
import com.kylix.core.model.RecipeList
import com.kylix.core.repositories.favorite.FavoriteRepository
import com.kylix.core.util.foldResult
import kotlinx.coroutines.flow.MutableStateFlow

class FavoriteScreenModel(
    private val repository: FavoriteRepository
): BaseScreenModel() {

    var favoriteState = MutableStateFlow(FavoriteState())
        private set

    fun getFavorites() {
        onSuspendProcess {
            repository.getFavoriteRecipes().foldResult(
                onSuccess = { result ->
                    favoriteState.value = favoriteState.value.copy(favorites = result)
                },
                onError = {
                    onDataError(it)
                }
            )
        }
    }

}

data class FavoriteState(
    val favorites: List<RecipeList> = emptyList()
)