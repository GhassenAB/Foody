package com.ghassenab.foody.data

import com.ghassenab.foody.data.network.FoodRecipesApi
import com.ghassenab.foody.models.FoodRecipe
import javax.inject.Inject
import retrofit2.Response

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }

}