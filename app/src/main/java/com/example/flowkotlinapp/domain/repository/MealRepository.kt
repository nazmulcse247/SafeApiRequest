package com.example.flowkotlinapp.domain.repository


import com.example.flowkotlinapp.common.ApiResult
import com.example.flowkotlinapp.domain.model.Meals
import com.example.flowkotlinapp.domain.use_case.GetMealUseCase
import kotlinx.coroutines.flow.Flow


interface MealRepository {

    suspend fun getMeals(s : GetMealUseCase.Params) : Flow<ApiResult<Meals>>
}