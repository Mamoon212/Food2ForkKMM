package com.example.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun RecipeDetailScreen(
    recipeId: Int?
) {
    if (recipeId == null) Text(text = "Something went wrong")
    else Text(text = "RecipeDetailScreen $recipeId")

}