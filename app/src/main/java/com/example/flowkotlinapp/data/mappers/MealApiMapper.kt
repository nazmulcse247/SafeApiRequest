package com.example.flowkotlinapp.data.mappers

import com.example.flowkotlinapp.data.model.MealsDTO
import com.example.flowkotlinapp.domain.model.Meal
import com.example.flowkotlinapp.domain.model.Meals
import javax.inject.Inject

class MealApiMapper @Inject constructor() : Mapper<MealsDTO, Meals> {
    override fun mapFromApiResponse(type: MealsDTO): Meals {
        return Meals(
            meals = type.meals?.map {
               Meal(
                   idMeal = it.idMeal ?: "",
                   strMeal = it.strMeal ?: "",
                   strMealThumb = it.strMealThumb ?: "",

               )
            }
        )
    }


}