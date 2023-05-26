package com.example.composerealstate.ui.common.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composerealstate.common.Constants
import com.example.composerealstate.ui.common.Screens

@Composable
fun MyBottomNavigationBar(
    navController: NavHostController,
) {
    val screens = listOf(
        Screens.ListHouses,
        Screens.About,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = Color.White,
    ) {
        screens.forEach { screen ->
            AddItem(
                screens = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screens: Screens,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    NavigationBarItem(
        icon = {
            Icon(
                imageVector = screens.icon,
                contentDescription = Constants.NAVIGATION_ICON_DESCRIPTION,
                modifier = Modifier.fillMaxSize(0.35f)
            )
        },
        selected = currentDestination?.hierarchy?.any { it.route == screens.route } == true,
        onClick = {
            navController.navigate(screens.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        alwaysShowLabel = false,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.Black,
            unselectedIconColor = Color.LightGray,
            indicatorColor = Color.White
        ),
        modifier = Modifier.fillMaxWidth()
    )
}