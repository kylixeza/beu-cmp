package com.kylix.core.data.remote.services

import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.post

class FavoriteService(
    private val client: HttpClient
) {
    suspend fun postFavoriteRecipe(recipeId: String) = client.post("recipes/$recipeId/favorites")
    suspend fun deleteFavoriteRecipe(recipeId: String) = client.delete("recipes/$recipeId/favorites")
}