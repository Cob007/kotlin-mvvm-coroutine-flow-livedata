package com.example.android.hilt.net

import com.example.android.hilt.data.Log
import com.google.gson.annotations.SerializedName


data class LogResponse(
    @SerializedName("status") val status : Boolean,
    val message : String,
    val data : List<Log>
)