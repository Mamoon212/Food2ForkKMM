package com.example.food2forkkmm.android.di

import com.example.food2forkkmm.datasource.cache.RecipeCache
import com.example.food2forkkmm.datasource.network.RecipeService
import com.example.food2forkkmm.interactors.recipe_detail.GetRecipe
import com.example.food2forkkmm.interactors.recipe_list.SearchRecipe
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {
    @Singleton
    @Provides
    fun provideSearchRecipe(recipeService: RecipeService, recipeCache: RecipeCache): SearchRecipe {
        return SearchRecipe(recipeService = recipeService, recipeCache = recipeCache)
    }

    @Singleton
    @Provides
    fun provideGetRecipe(recipeCache: RecipeCache): GetRecipe {
        return GetRecipe(recipeCache = recipeCache)
    }
}