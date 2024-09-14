package com.kylix.home

import beukmm.base.BaseScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import com.kylix.core.model.Category
import com.kylix.core.model.HomeRecipe
import com.kylix.core.repositories.profile.ProfileRepository
import com.kylix.core.repositories.recipe.RecipeRepository
import com.kylix.core.util.foldResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val recipeRepository: RecipeRepository,
    private val profileRepository: ProfileRepository
): BaseScreenModel() {

    var homeState = MutableStateFlow(HomeState())
        private set

    init {
        getHomeDate()
    }

    fun getHomeDate() {
        screenModelScope.launch {
            onSuspendProcess {
                greeting()
                getCategories()
                getHomeRecipes()
            }
        }
    }

    private suspend fun greeting() {
        profileRepository.greet().foldResult(
            onSuccess = { result ->
                Logger.i("Greeting: $result")
                homeState.update {
                    it.copy(greet = result)
                }
            },
            onError = {
                onDataError(it)
            }
        )
    }

    private suspend fun getCategories() {
        recipeRepository.getCategories().foldResult(
            onSuccess = { result ->
                Logger.i("Categories: $result")
                homeState.update {
                    it.copy(categories = result)
                }
            },
            onError = {
                onDataError(it)
            }
        )
    }

    private suspend fun getHomeRecipes() {
        recipeRepository.getHomeRecipes().foldResult(
            onSuccess = { result ->
                Logger.i("Home Recipes: $result")
                homeState.update {
                    it.copy(homeRecipes = result)
                }
            },
            onError = {
                onDataError(it)
            }
        )
    }

}

data class HomeState(
    val greet: String = "",
    val categories: List<Category> = emptyList(),
    val homeRecipes: List<HomeRecipe> = emptyList()
)