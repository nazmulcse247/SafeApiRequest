package com.example.flowkotlinapp

import android.content.ContentValues
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.coroutineScope
import com.example.flowkotlinapp.common.BaseActivity
import com.example.flowkotlinapp.databinding.ActivityMainBinding
import com.example.flowkotlinapp.domain.use_case.GetMealUseCase
import com.example.flowkotlinapp.presentation.ViewModel.MealUiState
import com.example.flowkotlinapp.presentation.ViewModel.MealViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel : MealViewModel by viewModels()

    override fun viewBindingLayout(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)


    override fun initializeView(savedInstanceState: Bundle?) {

        bindUiObserver()
    }

    private fun bindUiObserver()
    {

        viewModel.getMeals(GetMealUseCase.Params("chicken"))
        viewModel.mealList.execute { state->
            when(state){
                is MealUiState.ApiError -> {
                    binding.progressBar.visibility = View.GONE
                }
                is MealUiState.ApiSuccess -> {
                    Log.d("meal", "bindUiObserver value"+ state.data.meals.toString())
                    Toast.makeText(this@MainActivity,"size "+ state.data.meals?.size.toString(),Toast.LENGTH_LONG).show()
                    binding.progressBar.visibility = View.GONE
                }
                is MealUiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }
}


//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    binding = ActivityMainBinding.inflate(layoutInflater)
//    setContentView(binding!!.root)
//    //viewModel.getMeals("chicken")
//
////        lifecycle.coroutineScope.launchWhenCreated {
////            viewModel.mealList.collect {
////
////                if (it.isLoading) {
////
////                    binding!!.progressBar.visibility = View.VISIBLE
////                }
////                if (it.error.isNotBlank()) {
////
////                    binding!!.progressBar.visibility = View.GONE
////                }
////
////                it.data?.let {
////
////                    if (it.isEmpty()) {
////                        binding!!.progressBar.visibility = View.VISIBLE
////                    }
////
////                    Log.d(TAG, "list size"+ it.size.toString())
////                    Toast.makeText(this@MainActivity,"size "+ it.size.toString(),Toast.LENGTH_LONG).show()
////                    binding!!.progressBar.visibility = View.GONE
////
////                }
////
////
////            }
////        }
//}