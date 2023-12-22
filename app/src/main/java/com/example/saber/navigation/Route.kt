package com.example.saber.navigation

sealed class Route(val route: String) {
    object Dashboard : Route("dashboard")
    object History : Route("history")
    object Profile : Route("profile")
    object Detail : Route("detail/{chartId}"){
        fun createRoute(chartId:Int) = "detail/$chartId"
    }
}