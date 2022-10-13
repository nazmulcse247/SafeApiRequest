package com.example.flowkotlinapp.common

import com.example.flowkotlinapp.domain.model.Meal

data class MealState(

        val isLoading: Boolean = false,
        val data: List<Meal>? = null,
        val error: String = ""

)