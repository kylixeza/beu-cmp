package com.kylix.home.screens.category

import beukmm.base.BaseScreenModel
import com.kylix.core.model.RecipeList
import com.kylix.core.repositories.recipe.RecipeRepository
import com.kylix.core.util.foldResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class CategoryScreenModel(
    private val repository: RecipeRepository
): BaseScreenModel() {

    var categoryState = MutableStateFlow(CategoryState())
        private set

    fun getRecipes(categoryId: String) {
        onSuspendProcess {
            repository.getRecipesByCategory(categoryId).foldResult(
                onSuccess = { result ->
                    categoryState.update {
                        it.copy(recipes = result)
                    }
                },
                onError = { onDataError(it) }
            )
        }
    }
}

data class CategoryState(
    val recipes: List<RecipeList> = emptyList()
)