package com.example.sudokuapp.screens
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import model.SudokuBoard
import model.SudokuGenerator

@Composable
fun SudokuGameScreen(
    level : String
) {


}

@Composable
fun SudokuBoard(level: String){
    val sudokuBoard = SudokuGenerator(SudokuBoard(), level)


}


@Composable
@Preview(showBackground = true) //to show the background in the preview section
fun SudokuGameScreenPreview(){

}