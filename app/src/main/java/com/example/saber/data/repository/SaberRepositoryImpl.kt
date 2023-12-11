package com.example.saber.data.repository

import com.example.saber.data.response.DashboardResponse
import com.example.saber.data.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaberRepositoryImpl @Inject constructor(
    private val api: ApiService,
) : SaberRepository {
    override suspend fun getDailyPowerData(): DashboardResponse {
        return api.getDashboardData()
    }
}