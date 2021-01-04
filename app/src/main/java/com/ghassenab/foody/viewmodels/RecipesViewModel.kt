package com.ghassenab.foody.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ghassenab.foody.data.DataStoreRepository
import com.ghassenab.foody.util.Constants.Companion.API_KEY
import com.ghassenab.foody.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.ghassenab.foody.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.ghassenab.foody.util.Constants.Companion.DEFAULT_RECIPES_NUMBER
import com.ghassenab.foody.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.ghassenab.foody.util.Constants.Companion.QUERY_API_KEY
import com.ghassenab.foody.util.Constants.Companion.QUERY_DIET
import com.ghassenab.foody.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.ghassenab.foody.util.Constants.Companion.QUERY_NUMBER
import com.ghassenab.foody.util.Constants.Companion.QUERY_TYPE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class RecipesViewModel @ViewModelInject constructor(
    application: Application,
    private val dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {

    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE

    val readMealAndDietType = dataStoreRepository.readMealAndDietType

    fun saveMealAndDietType(mealType: String, mealTypeid: Int, dietType: String, dietTypeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveMealAndDietType(mealType, mealTypeid, dietType, dietTypeId)
        }
    }

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        viewModelScope.launch {
            readMealAndDietType.collect { values ->
                mealType = values.selectedMealType
                dietType = values.selectedDietType
            }
        }

        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = mealType
        queries[QUERY_DIET] = dietType
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }
}