package com.example.sudokuapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RewardScreen(
    score : Int,
    time  : Double,
    result: Boolean,
    navController: NavController
){
    if(result){
        Text(text = "RewardScreen with value: Scorre : $score  Time: $time")
    }
    else {
        Text(text = "Lost the game")
    }
}

@Composable
@Preview(showBackground = true) //to show the background in the preview section
fun RewardScreenPreview(){

}