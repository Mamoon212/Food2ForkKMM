package com.example.food2forkkmm.interactors.recipe_list

import com.example.food2forkkmm.datasource.network.RecipeService
import com.example.food2forkkmm.domain.model.Recipe
import com.example.food2forkkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipe(private val recipeService: RecipeService) {
    fun execute(page: Int, query: String): Flow<DataState<List<Recipe>>> = flow {
        emit(DataState.loading<List<Recipe>>())
        try {
            val recipes = recipeService.search(page, query)
            emit(DataState.data(null, recipes))
        } catch (e: Exception) {
            emit(DataState.error<List<Recipe>>(e.message ?: "Unknown error"))
        }
    }
}