package com.kylix.home.screens.category

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import beukmm.base.BaseScreenContent
import beukmm.components.BaseAppBar
import beukmm.components.RecipeItemVertical
import beukmm.di.koinScreenModel
import beukmm.navigator.SharedScreen
import beukmm.theme.Primary700
import beukmm.theme.White
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.lifecycle.LifecycleEffectOnce
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class CategoryScreen(
    private val categoryId: String,
    private val categoryName: String,
): Screen {

    @OptIn(ExperimentalVoyagerApi::class)
    @Composable
    override fun Content() {

        val screenModel = koinScreenModel<CategoryScreenModel>()
        val categoryState by screenModel.categoryState.collectAsState()
        val uiState by screenModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LifecycleEffectOnce {
            screenModel.getRecipes(categoryId)
        }

        BaseScreenContent(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                BaseAppBar(
                    title = categoryName,
                    leftIconTint = White,
                    onLeftIconClick = { navigator.pop() },
                    rightIcon = null
                )
            },
            uiState = uiState,
            statusBarColor = Primary700
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp)
            ) {
                items(categoryState.recipes) { recipe ->
                    RecipeItemVertical(
                        imageUrl = recipe.image,
                        difficulty = recipe.difficulty,
                        foodName = recipe.name,
                        favoritesCount = recipe.favorites,
                        rating = recipe.rating,
                        cookTime = recipe.estimationTime,
                        onItemClick = {
                            navigator.push(ScreenRegistry.get(SharedScreen.Detail(recipe.recipeId)))
                        }
                    )
                }
            }
        }
    }
}