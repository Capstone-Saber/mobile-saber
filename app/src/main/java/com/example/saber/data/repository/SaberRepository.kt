package com.example.saber.data.repository

import com.example.saber.data.model.DailyPower
import com.example.saber.data.response.DashboardDataItem
import com.example.saber.data.response.DashboardResponse
import kotlinx.coroutines.flow.Flow

interface SaberRepository {
    suspend fun getDailyPowerData():DashboardResponse
}