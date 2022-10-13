package com.example.flowkotlinapp.data.remote

import com.example.flowkotlinapp.data.model.MealsDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealSearchApi {

    @GET("api/json/v1/1/search.php")
    suspend fun getSearchMealList(
        @Query("s") query: String
    ): Response<MealsDTO>
}