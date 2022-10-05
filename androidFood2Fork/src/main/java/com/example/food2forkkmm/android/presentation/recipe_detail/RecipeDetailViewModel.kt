package com.example.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food2forkkmm.domain.model.Recipe
import com.example.food2forkkmm.interactors.recipe_detail.GetRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle, //not needed in this VM
    private val getRecipe: GetRecipe
) : ViewModel() {
    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    init {
        savedStateHandle.get<Int>("recipeId")?.let {
            viewModelScope.launch {
                getRecipe(it)
            }
        }
    }

    private fun getRecipe(recipeId: Int) {
        getRecipe.execute(recipeId)
            .onEach { dataState ->
                println(dataState.isLoading)
                dataState.data?.let {
                    println(it)
                    recipe.value = it
                }
                dataState.message?.let {
                    println(it)
                }

            }.launchIn(viewModelScope)
    }

}