package com.kylix.core.repositories.recipe

import com.github.michaelbull.result.Result
import com.kylix.core.base.BaseNetworkRequest
import com.kylix.core.data.remote.responses.BaseResponse
import com.kylix.core.data.remote.responses.recipe.CategoryResponse
import com.kylix.core.data.remote.responses.recipe.HomeRecipeResponse
import com.kylix.core.data.remote.responses.recipe.RecipeDetailResponse
import com.kylix.core.data.remote.services.RecipeService
import com.kylix.core.model.Category
import com.kylix.core.model.HomeRecipe
import com.kylix.core.model.RecipeDetail
import com.kylix.core.util.Error
import com.kylix.core.util.Success
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.ListSerializer

class RecipeRepositoryImpl(
    private val recipeService: RecipeService
): RecipeRepository {

    override suspend fun getCategories(): Result<Success<List<Category>>, Error> = object : BaseNetworkRequest<List<Category>, List<CategoryResponse>>() {
        override suspend fun createCall(): HttpResponse {
            return recipeService.getCategories()
        }

        override fun deserialize(responseJson: String): DeserializationStrategy<BaseResponse<List<CategoryResponse>>> {
            return BaseResponse.serializer(ListSerializer(CategoryResponse.serializer()))
        }

        override suspend fun List<CategoryResponse>.mapResponse(): List<Category> {
            return map { it.toCategory() }
        }
    }.run()

    override suspend fun getHomeRecipes(): Result<Success<List<HomeRecipe>>, Error> {
        return object : BaseNetworkRequest<List<HomeRecipe>, List<HomeRecipeResponse>>() {
            override suspend fun createCall(): HttpResponse {
                return recipeService.getHomeRecipes()
            }

            override fun deserialize(responseJson: String): DeserializationStrategy<BaseResponse<List<HomeRecipeResponse>>> {
                return BaseResponse.serializer(ListSerializer(HomeRecipeResponse.serializer()))
            }

            override suspend fun List<HomeRecipeResponse>.mapResponse(): List<HomeRecipe> {
                return map { it.toHomeRecipe() }
            }

        }.run()
    }

    override suspend fun getRecipeDetail(recipeId: String): Result<Success<RecipeDetail>, Error> {
        return object : BaseNetworkRequest<RecipeDetail, RecipeDetailResponse>() {
            override suspend fun createCall(): HttpResponse {
                return recipeService.getRecipeDetail(recipeId)
            }

            override fun deserialize(responseJson: String): DeserializationStrategy<BaseResponse<RecipeDetailResponse>> {
                return BaseResponse.serializer(RecipeDetailResponse.serializer())
            }

            override suspend fun RecipeDetailResponse.mapResponse(): RecipeDetail {
                return toRecipeDetail()
            }

        }.run()
    }

}
