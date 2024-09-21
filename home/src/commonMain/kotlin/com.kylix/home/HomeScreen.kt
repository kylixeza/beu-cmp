package com.kylix.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import beukmm.base.BaseScreenContent
import beukmm.components.RecipeItemHorizontal
import beukmm.di.koinScreenModel
import beukmm.navigator.SharedScreen
import beukmm.theme.Primary700
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.lifecycle.LifecycleEffectOnce
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kylix.home.components.CategoryItem
import com.kylix.home.components.HomeAppbar
import com.kylix.home.screens.search.SearchScreen

class HomeScreen : Screen {

    @OptIn(ExperimentalVoyagerApi::class)
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<HomeScreenModel>()
        val uiState by screenModel.uiState.collectAsState()
        val homeState by screenModel.homeState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LifecycleEffectOnce {
            screenModel.getHomeDate()
        }

        BaseScreenContent(
            modifier = Modifier.fillMaxWidth(),
            topBar = {
                HomeAppbar(
                    greeting = homeState.greet,
                    onSearchClick = { navigator.push(SearchScreen()) }
                )
            },
            uiState = uiState,
            onLoadingDialogDismissRequest = { screenModel.onFinishLoading() },
            statusBarColor = Primary700
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 12.dp, bottom = 86.dp)
            ) {

                item {
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                        text = "Categories",
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 12.dp)
                    ) {
                        items(homeState.categories) {
                            CategoryItem(category = it)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                items(homeState.homeRecipes) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                        text = it.title,
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    if (it.subtitle != null) {
                        Text(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                            text = it.subtitle.orEmpty(),
                            style = MaterialTheme.typography.body2
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 12.dp)
                    ) {
                        items(it.recipes) { recipe ->
                            RecipeItemHorizontal(
                                imageUrl = recipe.image,
                                isExclusive = false,
                                difficulty = recipe.difficulty,
                                foodName = recipe.name,
                                favoritesCount = recipe.favorites,
                                rating = recipe.rating,
                                cookTime = recipe.estimationTime,
                                onItemClick = {
                                    navigator.push(
                                       ScreenRegistry.get(
                                           SharedScreen.Detail(recipe.recipeId)
                                       )
                                    )
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}