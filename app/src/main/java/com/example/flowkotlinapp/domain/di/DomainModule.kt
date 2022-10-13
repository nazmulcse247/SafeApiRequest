package com.example.flowkotlinapp.domain.di

import com.example.flowkotlinapp.domain.repository.MealRepository
import com.example.flowkotlinapp.domain.use_case.GetMealUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object  DomainModule{

    @Provides
    @Singleton
    fun provideMealUseCase(mealRepository: MealRepository) : GetMealUseCase {
        return GetMealUseCase(mealRepository)
    }
}

