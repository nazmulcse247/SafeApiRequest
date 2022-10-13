package com.example.flowkotlinapp.domain.repository


import com.example.flowkotlinapp.common.ApiResult
import com.example.flowkotlinapp.domain.model.Meals
import kotlinx.coroutines.flow.Flow


interface MealRepository {

    suspend fun getMeals(s : String) : Flow<ApiResult<Meals>>
}