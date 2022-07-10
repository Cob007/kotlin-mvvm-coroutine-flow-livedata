package com.example.android.hilt.ui

import android.content.ComponentName
import androidx.hilt.work.HiltWorker
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.android.hilt.data.Log
import com.example.android.hilt.data.LoggerDataSource
import com.example.android.hilt.repo.LogRepository
import com.example.android.hilt.workmanager.SyncWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogsViewModel @Inject  constructor(
    private var logRepository : LogRepository,
    private var loggerDataSource: LoggerDataSource,
    private var worker: WorkManager
    ) : ViewModel() {

    var errorMessage = MutableLiveData<String>()
    var logsList = MutableLiveData<List<Log>>().apply { value = emptyList()  }

    fun getLogs(){
        viewModelScope.launch {
            loggerDataSource.getAllLogsFlow().collect {
                logsList.value = it
            }
        }
    }

    private fun getFromService(){
        viewModelScope.launch(Dispatchers.IO) {
            logRepository.getLogs().collect {
                if (it.status){
                    logsList.value = it.data
                }else{
                    errorMessage.value = it.message
                }
            }
        }
    }

    fun onButtonTapped(){
        getFromService()
    }

    fun onWorkerTapped() : Unit{
        val PACKAGE_NAME = "com.example.background.multiprocess"

        val serviceName = SyncWorker::class.java.name
        val componentName = ComponentName(PACKAGE_NAME, serviceName)

        val data: Data = Data.Builder()
            .putString("ARGUMENT_PACKAGE_NAME", componentName.packageName)
            .build()

        OneTimeWorkRequest.Builder(SyncWorker::class.java)
            .setInputData(data)
            .build()
    }

    override fun onCleared() {
        super.onCleared()
    }
}