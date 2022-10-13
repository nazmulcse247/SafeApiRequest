package com.example.flowkotlinapp.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowkotlinapp.common.ApiResult
import com.example.flowkotlinapp.common.MealState
import com.example.flowkotlinapp.domain.use_case.GetMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(private val getMealUseCase: GetMealUseCase) : BaseViewModel(){

    private val _mealList = MutableStateFlow(MealState())
    val mealList : StateFlow<MealState> = _mealList

//      fun getMeal(s : String){
//        getMealUseCase.execute(s).onEach {
//            when(it){
//                is ApiResult.Loading -> {
//                    _mealList.value = MealState(isLoading = true)
//                }
//
//                is ApiResult.Success -> {
//                    _mealList.value = MealState(data = it.data.meals)
//                }
//
//                is ApiResult.Error -> {
//                    _mealList.value = MealState(error = it.message ?: "")
//                }
//            }
//
//        }.launchIn(viewModelScope)
//    }


    fun getMeals(s : String) {
        execute {
            getMealUseCase.execute(s).onEach {
                when(it){
                is ApiResult.Loading -> {
                    _mealList.value = MealState(isLoading = true)
                }

                is ApiResult.Success -> {
                    _mealList.value = MealState(data = it.data.meals)
                }

                is ApiResult.Error -> {
                    _mealList.value = MealState(error = it.message ?: "")
                }
            }

        }
            }

    }

}