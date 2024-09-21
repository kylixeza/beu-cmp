package com.kylix.home.screens.search

import androidx.compose.runtime.mutableStateOf
import beukmm.base.BaseScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import com.kylix.core.model.RecipeList
import com.kylix.core.repositories.recipe.RecipeRepository
import com.kylix.core.util.foldResult
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchScreenModel(
    private val recipeRepository: RecipeRepository
) : BaseScreenModel() {

    var searchState = MutableStateFlow(SearchState())
        private set

    private val debounceQueryJob = mutableStateOf<Job?>(null)

    fun setQuery(query: String) {
        searchState.update { it.copy(displayQuery = query) }

        debounceQueryJob.value?.cancel()
        debounceQueryJob.value = screenModelScope.launch {
            delay(500)
            searchState.update { it.copy(query = query) }
            Logger.i { "Search query: $query" }
            searchRecipe()
        }
    }

    private fun searchRecipe() {
        if (searchState.value.query.isEmpty()) {
            searchState.update { it.copy(recipes = emptyList()) }
            onDataSuccess()
            return
        }
        onSuspendProcess {
            recipeRepository.searchRecipes(searchState.value.query).foldResult(
                onSuccess = { result ->
                    searchState.update { it.copy(recipes = result) }
                },
                onError = {
                    onDataError(it)
                }
            )
        }
    }
}

data class SearchState(
    val displayQuery: String = "",
    val query: String = "",
    val recipes: List<RecipeList> = emptyList(),
)