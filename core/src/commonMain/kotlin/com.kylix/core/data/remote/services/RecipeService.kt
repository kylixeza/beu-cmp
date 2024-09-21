package com.kylix.core.data.remote.services

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RecipeService(
    private val client: HttpClient,
) {

    suspend fun getCategories() = client.get("categories")

    suspend fun getHomeRecipes() = client.get("recipes/home")

    suspend fun getRecipeDetail(recipeId: String) = client.get("recipes/$recipeId")

    suspend fun searchRecipes(query: String) = client.get("recipes?query=$query")

    suspend fun getRecipesByCategory(categoryId: String) = client.get("recipes/categories/$categoryId")
}