package com.example.food2forkkmm.datasource.cache

import com.example.food2forkkmm.datasource.network.RecipeServiceImpl.Companion.PAGE_SIZE
import com.example.food2forkkmm.domain.model.Recipe
import com.example.food2forkkmm.domain.util.DatetimeUtil

class RecipeCacheImpl(
    private val recipeDatabase: RecipeDatabase,
    private val datetimeUtil: DatetimeUtil
) : RecipeCache {
    private val queries: RecipeDbQueries = recipeDatabase.recipeDbQueries

    override fun insert(recipe: Recipe) {
        with(recipe) {
            queries.insertRecipe(
                id = id.toLong(),
                title = title,
                publisher = publisher,
                featured_image = featuredImage,
                rating = rating.toLong(),
                source_url = sourceUrl,
                ingredients = ingredients.convertIngredientListToString(),
                date_updated = datetimeUtil.toEpochMilliseconds(dateUpdated),
                date_added = datetimeUtil.toEpochMilliseconds(dateAdded)
            )
        }
    }

    override fun insert(recipes: List<Recipe>) {
        recipes.forEach {
            insert(it)
        }
    }

    override fun search(query: String, page: Int): List<Recipe> {
        return queries.searchRecipes(
            query = query,
            pageSize = PAGE_SIZE.toLong(),
            offset = ((page - 1) * PAGE_SIZE).toLong()
        ).executeAsList().toRecipeList()
    }

    override fun getAll(page: Int): List<Recipe> {
        return queries.getAllRecipes(
            pageSize = PAGE_SIZE.toLong(),
            offset = ((page - 1) * PAGE_SIZE).toLong()
        ).executeAsList().toRecipeList()
    }

    override fun get(recipeId: Int): Recipe? {
        return try {
            queries.getRecipeById(id = recipeId.toLong()).executeAsOne().toRecipe()
        } catch (e: NullPointerException) {
            null
        }
    }
}