package com.example.android.hilt.data

import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

@ActivityScoped
class LoggerInMemoryDataSource @Inject constructor() : LoggerDataSource  {

    private var logs = LinkedList<Log>()

    override fun addLog(msg: String) {
        logs.addFirst(Log(msg, System.currentTimeMillis()))
    }

    override fun getAllLogs(callback: (List<Log>) -> Unit) {
        callback(logs)
    }

    override suspend fun getAllLogsFlow(): Flow<List<Log>> {
        TODO("Not yet implemented")
    }

    override fun removeLogs() {
        logs.clear()
    }
}