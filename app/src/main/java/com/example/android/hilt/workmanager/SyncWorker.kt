package com.example.android.hilt.workmanager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.android.hilt.data.LoggerLocalDataSource
import com.example.android.hilt.repo.LogRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted appContext : Context,
    @Assisted workerParams: WorkerParameters,
    private var logRepo : LogRepository,
    private var localDataSource: LoggerLocalDataSource
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        //withContext(Dispatchers.IO){
            val getAllPost = localDataSource.getAllLogsFlow()
            val response = logRepo.getLogs()
            return Result.success()
        //}
    }


}