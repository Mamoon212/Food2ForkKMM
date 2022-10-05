package com.example.food2forkkmm.interactors.recipe_list

import com.example.food2forkkmm.datasource.cache.RecipeCache
import com.example.food2forkkmm.datasource.network.RecipeService
import com.example.food2forkkmm.domain.model.Recipe
import com.example.food2forkkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipe(private val recipeService: RecipeService, private val recipeCache: RecipeCache) {
    fun execute(page: Int, query: String): Flow<DataState<List<Recipe>>> = flow {
        emit(DataState.loading<List<Recipe>>())
        try {
            val recipes = recipeService.search(page, query)
            recipeCache.insert(recipes)
            val cacheResult =
                if (query.isBlank()) recipeCache.getAll(page = page) else recipeCache.search(
                    query = query,
                    page = page
                )
            emit(DataState.data(null, cacheResult))
        } catch (e: Exception) {
            emit(DataState.error<List<Recipe>>(e.message ?: "Unknown error"))
        }
    }
}