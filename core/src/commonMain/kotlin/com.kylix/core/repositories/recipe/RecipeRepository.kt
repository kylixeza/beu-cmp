package com.kylix.core.repositories.recipe

import com.github.michaelbull.result.Result
import com.kylix.core.model.Category
import com.kylix.core.model.HomeRecipe
import com.kylix.core.model.RecipeDetail
import com.kylix.core.model.RecipeList
import com.kylix.core.util.Error
import com.kylix.core.util.Success

interface RecipeRepository {

    suspend fun getCategories(): Result<Success<List<Category>>, Error>
    suspend fun getHomeRecipes(): Result<Success<List<HomeRecipe>>, Error>
    suspend fun getRecipeDetail(recipeId: String): Result<Success<RecipeDetail>, Error>
    suspend fun searchRecipes(query: String): Result<Success<List<RecipeList>>, Error>
    suspend fun getRecipesByCategory(categoryId: String): Result<Success<List<RecipeList>>, Error>
    suspend fun getRelatedPredictionRecipes(query: String): Result<Success<List<RecipeList>>, Error>
}