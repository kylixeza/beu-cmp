package com.kylix.profile.screens.favorite

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import beukmm.base.BaseScreenContent
import beukmm.components.BaseAppBar
import beukmm.components.EmptyListScreen
import beukmm.components.RecipeItemVertical
import beukmm.di.koinScreenModel
import beukmm.navigator.SharedScreen
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.multiplatform.lifecycle.LifecycleEvent
import com.multiplatform.lifecycle.LifecycleObserver
import com.multiplatform.lifecycle.LocalLifecycleTracker

class FavoriteScreen: Screen {

    @Composable
    override fun Content() {

        val screenModel = koinScreenModel<FavoriteScreenModel>()
        val favoriteState by screenModel.favoriteState.collectAsState()
        val uiState by screenModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        val lifecycleTracker = LocalLifecycleTracker.current

        BaseScreenContent(
            topBar = {
                BaseAppBar(
                    title = "Favorite",
                    onLeftIconClick = { navigator.pop() },
                    rightIcon = null
                )
            },
            uiState = uiState,
            onLoadingDialogDismissRequest = { screenModel.onFinishLoading() }
        ) { innerPadding ->

            if (favoriteState.favorites.isEmpty()) {
                EmptyListScreen(
                    modifier = Modifier.fillMaxSize(),
                    message = "Start loving one of our recipes to see them here! 😍"
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(innerPadding),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
            ) {
                items(favoriteState.favorites) { recipe ->
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

        DisposableEffect(Unit) {
            val listener =
                object : LifecycleObserver {
                    override fun onEvent(event: LifecycleEvent) {
                        if (event == LifecycleEvent.OnResumeEvent) {
                            screenModel.getFavorites()
                        }
                    }
                }
            lifecycleTracker.addObserver(listener)
            onDispose {
                lifecycleTracker.removeObserver(listener)
            }
        }
    }
}