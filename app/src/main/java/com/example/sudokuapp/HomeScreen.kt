package com.example.sudokuapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable{navController.navigate(route = Screen.Detail.route)},
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Sudoku",
            color = Color.Black,
            fontWeight = FontWeight.Bold, // Use FontWeight
            fontSize = 40.sp
        )
    }
}

@Composable
@Preview(showBackground = true) //to show the background in the preview section
fun HomeScreenPreview(){
    HomeScreen(
        navController = rememberNavController()
    )
}