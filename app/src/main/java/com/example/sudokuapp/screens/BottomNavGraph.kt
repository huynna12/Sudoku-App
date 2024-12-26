package com.example.sudokuapp.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = BottomBar.Home.route
    ){
        composable(route = BottomBar.Home.route){
            DetailScreen()
        }

        composable(route = BottomBar.Profile.route){
            ProfileScreen()
        }

        composable(route = BottomBar.Settings.route){
            SettingsScreen()        }
    }
}