package com.example.food2forkkmm.datasource.cache

import com.example.food2forkkmm.domain.model.Recipe
import com.example.food2forkkmm.domain.util.DatetimeUtil
import com.squareup.sqldelight.db.SqlDriver

class RecipeDatabaseFactory(private val driverFactory: DriverFactory) {
    fun createDatabase(): RecipeDatabase = RecipeDatabase(driverFactory.createDriver())
}

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun Recipe_Entity.toRecipe() =
    Recipe(
        id = id.toInt(),
        title = title,
        publisher = publisher,
        featuredImage = featured_image,
        rating = rating.toInt(),
        sourceUrl = source_url,
        ingredients = ingredients.convertIngredientsStringToList(),
        dateAdded = DatetimeUtil().toLocalDate(date_added),
        dateUpdated = DatetimeUtil().toLocalDate(date_updated)
    )

fun List<Recipe_Entity>.toRecipeList() =
    map { it.toRecipe() }

fun List<String>.convertIngredientListToString(): String {
    val ingredientsString = StringBuilder()
    this.forEach {
        ingredientsString.append("$it,")
    }
    return ingredientsString.toString()
}

fun String.convertIngredientsStringToList(): List<String> = split(",")

