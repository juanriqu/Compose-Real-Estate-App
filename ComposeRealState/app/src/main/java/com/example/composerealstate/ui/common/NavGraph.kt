package com.example.composerealstate.ui.common

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.composerealstate.common.Constants
import com.example.composerealstate.ui.common.components.MyBottomNavigationBar
import com.example.composerealstate.ui.screens.about.AboutScreen
import com.example.composerealstate.ui.screens.detailedhouse.DetailedHouseScreen
import com.example.composerealstate.ui.screens.listhouses.ListHousesScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.ListHouses.route,
    ) {
        composable(route = Screens.ListHouses.route) {
            ListHousesScreen(
                bottomBar = {
                    MyBottomNavigationBar(
                        navController = navController,
                    )
                },
                onNavigateToDetail = { houseId ->
                    navController.navigate(
                        Screens.DetailedHouse.withArgs(houseId)
                    )
                }
            )
        }
        composable(
            route = Constants.DETAILED_HOUSE_SCREEN_ROUTE_WITH_ARGS,
            arguments = listOf(
                navArgument(name = Constants.ARG_DETAILED_HOUSE_SCREEN_HOUSE_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            val houseId = it.arguments?.getInt(Constants.ARG_DETAILED_HOUSE_SCREEN_HOUSE_ID)
            DetailedHouseScreen(
                houseId = houseId ?: 0,
                onBack = {
                    navController.popBackStack()
                },
            )
        }
        composable(route = Screens.About.route) {
            AboutScreen(
                bottomBar = {
                    MyBottomNavigationBar(
                        navController = navController,
                    )
                },
            )
        }
    }
}