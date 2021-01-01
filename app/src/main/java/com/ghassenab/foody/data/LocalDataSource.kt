package com.ghassenab.foody.data

import com.ghassenab.foody.data.database.RecipesDao
import com.ghassenab.foody.data.database.RecipesEntity
import com.ghassenab.foody.models.FoodRecipe
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {

    fun readDatabase(): Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()
    }

    suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        return recipesDao.insertRecipes(recipesEntity)
    }
}