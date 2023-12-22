package com.example.saber.data.retrofit

import com.example.saber.data.response.DashboardDataItem
import com.example.saber.data.response.DashboardResponse
import retrofit2.http.GET

interface ApiService {
    @GET("electricities/SqPQURHEfDYji5hNhJhu/usages?date=2023-12-07")
    suspend fun getDashboardData():DashboardResponse
}