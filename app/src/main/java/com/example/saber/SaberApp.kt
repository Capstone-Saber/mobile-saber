package com.example.saber

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.saber.navigation.NavigationItem
import com.example.saber.navigation.Route
import com.example.saber.ui.dashboard.DashboardPage
import com.example.saber.ui.history.HistoryPage
import com.example.saber.ui.profile.ProfilePage


@Composable
fun SaberApp(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            BottomBar(
                navHostController = navHostController,
                modifier = modifier,
                currentRoute = currentRoute
            )
        }
    ) { padding ->
        NavHost(
            navController = navHostController,
            startDestination = Route.Dashboard.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Route.Dashboard.route) {
                DashboardPage(modifier = modifier)
            }
            composable(Route.History.route) {
                HistoryPage(modifier = modifier)
            }
            composable(Route.Profile.route) {
                ProfilePage(modifier = modifier)
            }
        }
    }
}

@Composable
private fun BottomBar(
    modifier: Modifier,
    navHostController: NavHostController,
    currentRoute: String?,
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_dashboard),
                icon = Icons.Default.Home,
                route = Route.Dashboard
            ),
            NavigationItem(
                title = stringResource(R.string.menu_history),
                icon = Icons.Default.Refresh,
                route = Route.History
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                route = Route.Profile
            )
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.route.route,
                onClick = {
                    navHostController.navigate(item.route.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}