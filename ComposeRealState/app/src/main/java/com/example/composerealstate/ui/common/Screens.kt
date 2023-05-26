package com.example.composerealstate.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.composerealstate.common.Constants

sealed class Screens(
    val route: String,
    val icon: ImageVector,
    val title: String
) {
    object ListHouses : Screens(
        route = Constants.LIST_HOUSES_SCREENS_ROUTE,
        Icons.Filled.Home,
        Constants.LIST_HOUSES_Screens_TITLE
    )

    object DetailedHouse : Screens(
        Constants.DETAILED_HOUSE_SCREEN_ROUTE,
        Icons.Filled.Home,
        Constants.DETAILED_HOUSE_SCREEN_TITLE
    ) {
        fun withArgs(houseId: Int): String {
            return buildString {
                append(DetailedHouse.route)
                append("/$houseId")
            }
        }
    }

    object About : Screens(
        Constants.ABOUT_SCREEN_ROUTE,
        Icons.Filled.Info,
        Constants.ABOUT_SCREEN_TITLE
    )
}
