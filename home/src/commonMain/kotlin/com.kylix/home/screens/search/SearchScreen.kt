package com.kylix.home.screens.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import beukmm.base.BaseScreenContent
import beukmm.components.BeuBasicTextField
import beukmm.components.RecipeItemVertical
import beukmm.components.SecondaryAppBar
import beukmm.di.koinScreenModel
import beukmm.navigator.SharedScreen
import beukmm.theme.Primary700
import beukmm.theme.White
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

class SearchScreen: Screen {

    @Composable
    override fun Content() {

        val screenModel = koinScreenModel<SearchScreenModel>()
        val searchState by screenModel.searchState.collectAsState()
        val uiState by screenModel.uiState.collectAsState()

        val navigator = LocalNavigator.current

        BaseScreenContent(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                SecondaryAppBar(
                    title = "Search",
                    titleColor = White,
                    leftIconTint = White,
                    background = Primary700,
                    onLeftIconClick = {
                        navigator?.pop()
                    },
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    BeuBasicTextField(
                        value = searchState.displayQuery,
                        onValueChange = { screenModel.setQuery(it) },
                        label = "Search",
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        trailingIcon = {
                        }
                    )
                }
            },
            statusBarColor = Primary700,
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp)
            ) {
                items(
                    searchState.recipes,
                    key = { it.recipeId }
                ) { recipe ->
                    RecipeItemVertical(
                        imageUrl = recipe.image,
                        difficulty = recipe.difficulty,
                        foodName = recipe.name,
                        favoritesCount = recipe.favorites,
                        rating = recipe.rating,
                        cookTime = recipe.estimationTime,
                        onItemClick = {
                            navigator?.push(ScreenRegistry.get(SharedScreen.Detail(recipe.recipeId)))
                        }
                    )
                }
            }
        }
    }
}