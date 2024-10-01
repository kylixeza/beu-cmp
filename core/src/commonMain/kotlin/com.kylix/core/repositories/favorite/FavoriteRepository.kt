package com.kylix.core.repositories.favorite

import com.github.michaelbull.result.Result
import com.kylix.core.model.RecipeList
import com.kylix.core.util.Error
import com.kylix.core.util.Success

interface FavoriteRepository {
    suspend fun postFavoriteRecipe(recipeId: String): Result<Success<Unit>, Error>
    suspend fun deleteFavoriteRecipe(recipeId: String): Result<Success<Unit>, Error>
    suspend fun getFavoriteRecipes(): Result<Success<List<RecipeList>>, Error>
}