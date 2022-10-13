package com.example.flowkotlinapp.di

import com.example.flowkotlinapp.common.Utils.BASE_URL
import com.example.flowkotlinapp.data.mappers.MealApiMapper
import com.example.flowkotlinapp.data.network.utils.SafeApiRequest
import com.example.flowkotlinapp.data.remote.MealSearchApi
import com.example.flowkotlinapp.data.repository.MealRepositoryImpl
import com.example.flowkotlinapp.domain.repository.MealRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltModule {

    @Provides
    @Singleton
    fun getMealSearchApi() : MealSearchApi {

        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MealSearchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMealSearchRepository(mealSearchApi: MealSearchApi,mealApiMapper: MealApiMapper,safeApiRequest: SafeApiRequest) : MealRepository{
        return MealRepositoryImpl(mealSearchApi,mealApiMapper,safeApiRequest)
    }


}