package com.kylix.core.repositories.favorite

import com.github.michaelbull.result.Result
import com.kylix.core.base.BaseNetworkRequest
import com.kylix.core.data.remote.responses.BaseResponse
import com.kylix.core.data.remote.responses.recipe.RecipeListResponse
import com.kylix.core.data.remote.services.FavoriteService
import com.kylix.core.model.RecipeList
import com.kylix.core.util.Error
import com.kylix.core.util.Success
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer

class FavoriteRepositoryImpl(
    private val favoriteService: FavoriteService
): FavoriteRepository {

    override suspend fun postFavoriteRecipe(recipeId: String): Result<Success<Unit>, Error> {
        return object : BaseNetworkRequest<Unit, String>() {
            override suspend fun createCall(): HttpResponse {
                return favoriteService.postFavoriteRecipe(recipeId)
            }

            override fun deserialize(responseJson: String): DeserializationStrategy<BaseResponse<String>> {
                return BaseResponse.serializer(String.serializer())
            }

            override suspend fun String.mapResponse() {
                // Do nothing
            }

        }.run()
    }

    override suspend fun deleteFavoriteRecipe(recipeId: String): Result<Success<Unit>, Error> {
        return object : BaseNetworkRequest<Unit, String>() {
            override suspend fun createCall(): HttpResponse {
                return favoriteService.deleteFavoriteRecipe(recipeId)
            }

            override fun deserialize(responseJson: String): DeserializationStrategy<BaseResponse<String>> {
                return BaseResponse.serializer(String.serializer())
            }

            override suspend fun String.mapResponse() {
                // Do nothing
            }
        }.run()
    }

    override suspend fun getFavoriteRecipes(): Result<Success<List<RecipeList>>, Error> {
        return object : BaseNetworkRequest<List<RecipeList>, List<RecipeListResponse>>() {
            override suspend fun createCall(): HttpResponse {
                return favoriteService.getFavoriteRecipes()
            }

            override fun deserialize(responseJson: String): DeserializationStrategy<BaseResponse<List<RecipeListResponse>>> {
                return BaseResponse.serializer(ListSerializer(RecipeListResponse.serializer()))
            }

            override suspend fun List<RecipeListResponse>.mapResponse(): List<RecipeList> {
                return map { it.toRecipeList() }
            }
        }.run()
    }
}