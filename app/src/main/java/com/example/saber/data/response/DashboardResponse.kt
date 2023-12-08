package com.example.saber.data.response

import com.google.gson.annotations.SerializedName

data class DashboardResponse (
    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("message")
    val message:String,

    @field:SerializedName("data")
    val data:List<DashboardDataItem> = emptyList()
)

data class DashboardDataItem(
    @field:SerializedName("timestamp")
    val status: String,

    @field:SerializedName("avgPower")
    val message:Int,
)
