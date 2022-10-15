package com.example.flowkotlinapp.presentation.ViewModel


import com.example.flowkotlinapp.common.ApiResult
import com.example.flowkotlinapp.domain.model.Meals
import com.example.flowkotlinapp.domain.use_case.GetMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(private val getMealUseCase: GetMealUseCase) : BaseViewModel(){

    private val _mealList = MutableStateFlow<MealUiState<Any>>(MealUiState.Loading(true))
    val mealList get() = _mealList

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


    fun getMeals(s : GetMealUseCase.Params) {
        execute {
            getMealUseCase.execute(s).collect {
                when(it){
                is ApiResult.Loading -> {
                    _mealList.value = MealUiState.Loading(it.loading)
                }

                is ApiResult.Success -> {
                    _mealList.value = MealUiState.ApiSuccess(it.data)
                }

                is ApiResult.Error -> {
                    _mealList.value = MealUiState.ApiError(it.message)
                }
            }

        }
            }

    }

}

sealed class MealUiState<out R>{
    data class Loading(val isLoading : Boolean ) : MealUiState<Loading>()
    data class ApiError(val message : String) : MealUiState<ApiError>()
    data class ApiSuccess(val data : Meals) : MealUiState<Loading>()
}

sealed class MealUiAction{
    data class FetchMeal(val params : GetMealUseCase) : MealUiAction()
}