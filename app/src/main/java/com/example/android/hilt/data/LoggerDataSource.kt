package com.example.android.hilt.data

import kotlinx.coroutines.flow.Flow

interface LoggerDataSource {
    fun addLog(msg : String)
    fun getAllLogs(callback : (List<Log>) -> Unit)
    suspend fun getAllLogsFlow(): Flow<List<Log>>
    fun removeLogs()
}