package com.example.sudokuapp.screens

import SudokuBoardScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import model.SudokuBoard
import model.SudokuGenerator

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
            val initialBoard = SudokuBoard()
            val generator = SudokuGenerator(initialBoard, difficulty)

            SudokuBoardScreen(generator.getBoard(), navController)
        }

        composable(
            route = Screen.Reward.route,
            arguments = listOf(
                navArgument("score") { type = NavType.IntType },
                navArgument("time") { type = NavType.IntType },
                navArgument("result") { type = NavType.BoolType }
            )
        ) { backStackEntry ->
            val score = backStackEntry.arguments?.getInt("score") ?: 0
            val time = backStackEntry.arguments?.getInt("time") ?: 0
            val result = backStackEntry.arguments?.getBoolean("result") ?: false
            RewardScreen(score = score, time = time, result = result, navController = navController) // Pass time directly
        }
    }
}