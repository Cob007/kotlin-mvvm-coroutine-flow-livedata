package com.example.android.hilt.repo

import com.example.android.hilt.data.Log
import com.example.android.hilt.data.LogDao
import com.example.android.hilt.net.ApiService
import com.example.android.hilt.net.LogResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface LogRepository {
    suspend fun getLogs(): Flow<LogResponse>
}

class LogRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : LogRepository {
    override suspend fun getLogs(): Flow<LogResponse> = flow {
        val res = apiService.getCountries()
        emit(res)
    }

}