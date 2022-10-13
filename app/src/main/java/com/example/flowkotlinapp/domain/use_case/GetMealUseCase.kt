package com.example.flowkotlinapp.domain.use_case

import com.example.flowkotlinapp.common.ApiResult
import com.example.flowkotlinapp.domain.model.Meals
import com.example.flowkotlinapp.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealUseCase @Inject constructor(private val repository: MealRepository) : ApiUseCaseParams<String,Meals>{
    override suspend fun execute(params: String): Flow<ApiResult<Meals>> {
        return repository.getMeals(params)
    }

}