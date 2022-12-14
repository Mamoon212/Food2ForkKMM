package com.example.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.food2forkkmm.domain.model.Recipe

@Composable
fun RecipeDetailScreen(
    recipe: Recipe?
) {
    if (recipe == null) Text(text = "Something went wrong")
    else Text(text = "RecipeDetailScreen $recipe")

}