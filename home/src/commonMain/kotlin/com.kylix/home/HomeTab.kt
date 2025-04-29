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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import beukmm.base.BaseScreenContent
import beukmm.components.RecipeItemHorizontal
import beukmm.di.koinNavigatorScreenModel
import beukmm.navigator.SharedScreen
import beukmm.theme.Primary500
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.kylix.home.components.CategoryItem
import com.kylix.home.components.HomeAppbar
import com.kylix.home.screens.category.CategoryScreen
import com.kylix.home.screens.search.SearchScreen
import compose.icons.feathericons.Home

object HomeTab : Tab {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val navigatorParent = navigator.parent ?: return

        val screenModel = navigatorParent.koinNavigatorScreenModel<HomeScreenModel>()
        val uiState by screenModel.uiState.collectAsState()
        val homeState by screenModel.homeState.collectAsState()

        BaseScreenContent(
            modifier = Modifier.fillMaxWidth(),
            topBar = {
                HomeAppbar(
                    greeting = homeState.greet,
                    onSearchClick = { navigatorParent.push(SearchScreen()) }
                )
            },
            uiState = uiState,
            onLoadingDialogDismissRequest = { screenModel.onFinishLoading() },
            statusBarColor = Primary500
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentPadding = PaddingValues(top = 12.dp, bottom = 86.dp)
            ) {

                item {
                      Text(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                        text = "Categories",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 12.dp)
                    ) {
                        items(homeState.categories) {
                            CategoryItem(
                                category = it,
                                onCategorySelected = { id, name ->
                                    navigatorParent.push(
                                        CategoryScreen(
                                            categoryId = id,
                                            categoryName = name
                                        )
                                    )
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                items(homeState.homeRecipes) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                        text = it.title,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    if (it.subtitle != null) {
                        Text(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                            text = it.subtitle.orEmpty(),
                            style = MaterialTheme.typography.bodyMedium
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
                                    navigatorParent.push(
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

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(image = compose.icons.FeatherIcons.Home)

            return remember {
                TabOptions(
                    index = 0u,
                    icon = icon,
                    title = "Home"
                )
            }
        }
}