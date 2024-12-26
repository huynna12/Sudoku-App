package com.example.sudokuapp.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBar(
    val route : String,
    val title : String,
    val icon: ImageVector
){
    object Home : BottomBar(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Profile : BottomBar(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )

    object Settings : BottomBar(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )
}