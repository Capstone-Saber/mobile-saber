package com.example.saber.data.retrofit

import com.example.saber.data.response.DashboardResponse
import retrofit2.http.GET

interface ApiService {
    @GET("dashboard")
    suspend fun getDashboardData():DashboardResponse
}