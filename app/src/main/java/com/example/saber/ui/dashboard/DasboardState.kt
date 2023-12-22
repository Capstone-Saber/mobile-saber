package com.example.saber.ui.dashboard

import com.example.saber.data.response.DashboardDataItem

data class DashboardState(
    val isLoading: Boolean = false,
    val dataItems: List<DashboardDataItem> = emptyList(),
    val error: String = ""
)