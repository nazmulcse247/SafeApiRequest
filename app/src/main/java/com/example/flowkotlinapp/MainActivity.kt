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
import com.example.flowkotlinapp.databinding.ActivityMainBinding
import com.example.flowkotlinapp.presentation.ViewModel.MealViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null

    private val viewModel : MealViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        viewModel.getMeals("chicken")

        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.mealList.collect {

                if (it.isLoading) {

                    binding!!.progressBar.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {

                    binding!!.progressBar.visibility = View.GONE
                }

                it.data?.let {

                    if (it.isEmpty()) {
                        binding!!.progressBar.visibility = View.VISIBLE
                    }

                    Log.d(TAG, "list size"+ it.size.toString())
                    Toast.makeText(this@MainActivity,"size "+ it.size.toString(),Toast.LENGTH_LONG).show()
                    binding!!.progressBar.visibility = View.GONE

                }


            }
        }





    }
}