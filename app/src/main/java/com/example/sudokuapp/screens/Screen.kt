package com.example.sudokuapp.screens

sealed class Screen (val route: String){
    object Home : Screen(route = "home")
    object Detail : Screen(route = "detail")
    object SudokuBoard : Screen(route = "sudokuBoard/{difficulty}")
    object Reward : Screen(route = "reward/{score}/{time}/{result}")
}