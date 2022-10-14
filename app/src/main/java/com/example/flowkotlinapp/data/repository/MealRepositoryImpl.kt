package com.example.flowkotlinapp.data.repository

import com.example.flowkotlinapp.common.ApiResult
import com.example.flowkotlinapp.data.mappers.MealApiMapper
import com.example.flowkotlinapp.data.mappers.mapFromApiResponse
import com.example.flowkotlinapp.data.network.utils.SafeApiRequest
import com.example.flowkotlinapp.data.remote.MealSearchApi
import com.example.flowkotlinapp.domain.model.Meals
import com.example.flowkotlinapp.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealSearchApi: MealSearchApi,
    private val mealApiMapper: MealApiMapper,

) : MealRepository , SafeApiRequest() {
    override suspend fun getMeals(s : String): Flow<ApiResult<Meals>> {
        return mapFromApiResponse(
            downloadData {
                mealSearchApi.getSearchMealList(s)
            },mealApiMapper
        )
    }

}