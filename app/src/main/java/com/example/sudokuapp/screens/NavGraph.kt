package com.example.sudokuapp.screens

import SudokuBoardScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController)
        }

        composable(
            route = Screen.Detail.route
        ) {
            DetailScreen(navController)
//            MainScreen()
        }

        // Add the new screen for the Sudoku board
        composable( route = Screen.SudokuBoard.route) { backStackEntry ->
            val difficulty = backStackEntry.arguments?.getString("difficulty") ?: "Easy" // Default to "Easy" if missing
            SudokuBoardScreen(difficulty = difficulty)
        }
    }
}