package com.example.flowkotlinapp.data.network.utils

import android.content.ContentValues.TAG
import android.util.Log
import com.example.flowkotlinapp.common.ApiResult
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

abstract class SafeApiRequest {

    suspend fun<ResultType> downloadData(api : suspend () -> Response<ResultType>): Flow<ApiResult<ResultType>> {
        return withContext(Dispatchers.IO) {
            flow {
                try {
                    emit(ApiResult.Loading(true))
                    val response: Response<ResultType> = api()
                    emit(ApiResult.Loading(false))
                    if (response.isSuccessful){
                        response.body()?.let {
                            emit(ApiResult.Success(data = it))
                            Log.d(TAG, "downloadData: "+ response.body().toString())
                        }?: emit(ApiResult.Error(message = "Unknown error occurred", code = 0))
                    }else{
                        emit(ApiResult.Error(message = "", code = response.code()))
                    }
                }catch (e:Exception){
                    emit(ApiResult.Loading(false))
                    emit(ApiResult.Error(message = "", code =0))
                }
            }
        }
    }
}