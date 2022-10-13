package com.example.flowkotlinapp.domain.use_case

import com.example.flowkotlinapp.common.ApiResult
import kotlinx.coroutines.flow.Flow

interface UseCase

interface ApiUseCaseParams<Params, Type> : UseCase {
    suspend fun execute(params: Params): Flow<ApiResult<Type>>
}

interface ApiUseCaseNonParams<Type> : UseCase {
    suspend fun execute(): Flow<ApiResult<Type>>
}