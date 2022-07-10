package com.example.android.hilt.net

import retrofit2.http.GET

interface ApiService {
    @GET("region/europe")
    suspend fun getCountries(): LogResponse
}