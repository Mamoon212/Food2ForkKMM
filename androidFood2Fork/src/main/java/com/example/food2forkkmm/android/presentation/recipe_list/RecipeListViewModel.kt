package com.example.food2forkkmm.android.presentation.recipe_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food2forkkmm.interactors.recipe_list.SearchRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle, //not needed in this VM,
    private val searchRecipe: SearchRecipe
) : ViewModel() {

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        searchRecipe.execute(
            page = 1,
            query = "chicken"
        ).onEach { dataState ->
            println(dataState.isLoading)
            dataState.data?.let {
                println(it)
            }
            dataState.message?.let {
                println(it)
            }
        }.launchIn(viewModelScope)
    }
}