package com.ghassenab.foody

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import com.ghassenab.foody.models.FoodRecipe


interface FoodRecipesApi {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries: Map<String, String>
    ): Response<FoodRecipe>

}